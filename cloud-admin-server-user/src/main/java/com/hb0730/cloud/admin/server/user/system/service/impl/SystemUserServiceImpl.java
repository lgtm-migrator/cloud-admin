package com.hb0730.cloud.admin.server.user.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hb0730.clou.admin.commons.model.role.SystemRoleVO;
import com.hb0730.cloud.admin.common.exception.BusinessException;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.commons.dept.model.vo.SystemDeptVO;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.commons.permission.model.vo.SystemPermissionVO;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.commons.user.dept.model.vo.SystemUserDeptVO;
import com.hb0730.cloud.admin.commons.user.dept.model.vo.UserDeptParamsVO;
import com.hb0730.cloud.admin.server.user.feign.IRemoteUserRole;
import com.hb0730.cloud.admin.server.user.handler.DeptHandler;
import com.hb0730.cloud.admin.server.user.handler.RolePermissionHandler;
import com.hb0730.cloud.admin.server.user.handler.UserPostHandler;
import com.hb0730.cloud.admin.server.user.handler.UserRoleHandler;
import com.hb0730.cloud.admin.server.user.system.mapper.SystemUserMapper;
import com.hb0730.cloud.admin.server.user.system.model.entity.SystemUserEntity;
import com.hb0730.cloud.admin.server.user.system.model.vo.SystemUserVO;
import com.hb0730.cloud.admin.server.user.system.model.vo.UserParams;
import com.hb0730.cloud.admin.server.user.system.model.vo.UserSaveVO;
import com.hb0730.cloud.admin.server.user.system.service.ISystemUserService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-20
 */
@Service
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUserMapper, SystemUserEntity> implements ISystemUserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserPostHandler userPostHandler;
    @Autowired
    private UserRoleHandler userRoleHandler;
    @Autowired
    private RolePermissionHandler rolePermissionHandler;
    @Autowired
    private DeptHandler deptHandler;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional(name = "cloud-admin-user-save-seata")
    public boolean save(@NonNull UserSaveVO saveVO, @NonNull UserDetail userDetail) {
        verify(saveVO, false);
        //用户加密
        String password = passwordEncode(saveVO.getPassword());
        saveVO.setPassword(password);
        saveVO.setCreateTime(new Date());
        saveVO.setCreateUserId(userDetail.getId());
        SystemUserEntity entity = BeanUtils.transformFrom(saveVO, SystemUserEntity.class);
        boolean save = save(entity);
        assert entity != null;
        remote(entity.getId(), saveVO);
        return save;
    }

    @Override
    public UserDetail findUserByUserName(String username) {
        SystemUserEntity entity = new SystemUserEntity();
        entity.setUsername(username);
        QueryWrapper<SystemUserEntity> queryWrapper = new QueryWrapper<>(entity);
        SystemUserEntity e1 = getOne(queryWrapper);
        if (Objects.isNull(e1)) {
            throw new BusinessException("根据用户账号获取用户失败,用户为空");
        }
        UserDetail userDetail = BeanUtils.transformFrom(e1, UserDetail.class);
        //获取权限
        assert userDetail != null;
        List<SystemPermissionVO> permission = getPermissionByUserId(userDetail.getId());
        userDetail.setUserPermission(permission);
        //组织
        SystemDeptVO deptInfo = deptHandler.getDeptByUserId(userDetail.getId());
        userDetail.setUserDept(deptInfo);
        //角色
        List<SystemRoleVO> roleList = userRoleHandler.getRoleInfoByUserId(userDetail.getId());
        userDetail.setUserRole(roleList);
        return userDetail;
    }

    @Override
    public PageInfo getUserPage(@NonNull Integer page, @NonNull Integer pageSize, UserParams params) {
        UserDeptParamsVO userDeptParams = new UserDeptParamsVO();
        userDeptParams.setDeptId(params.getDeptId());
        PageInfo pageInfo = deptHandler.getPage(page, pageSize, userDeptParams);
        List resultList = pageInfo.getList();
        if (CollectionUtils.isEmpty(resultList)) {
            return pageInfo;
        }
        List<SystemUserDeptVO> resultUserDept = GsonUtils.json2List(GsonUtils.json2String(resultList), SystemUserDeptVO.class);
        List<Long> userIds = resultUserDept.parallelStream().map(SystemUserDeptVO::getUserId).collect(Collectors.toList());
        List<SystemUserEntity> userEntity = listByIds(userIds);
        List<SystemUserVO> userList = BeanUtils.transformFromInBatch(userEntity, SystemUserVO.class);
        pageInfo.setList(userList);
        return pageInfo;
    }

    @Override
    public UserSaveVO getUserInfo(@NonNull Long userId) {
        Long deptId = deptHandler.getDeptIdByUserId(userId);
        List<Long> postIds = userPostHandler.getPostIdByUserId(userId);
        List<Long> roleIds = userRoleHandler.getRoleIdByUserId(userId);
        SystemUserEntity entity = getById(userId);
        UserSaveVO userVO = BeanUtils.transformFrom(entity, UserSaveVO.class);
        assert userVO != null;
        userVO.setDeptId(deptId);
        userVO.setPosts(postIds);
        userVO.setRoles(roleIds);
        return userVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional(name = "cloud-admin-user-save-seata")
    public boolean updateById(@NonNull Long userId, UserSaveVO saveVO, @NonNull UserDetail userDetail) {
        verify(saveVO, true);
        SystemUserEntity entity = getById(userId);
        saveVO.setPassword(null);
        entity.setUpdateUserId(userDetail.getId());
        entity.setUpdateTime(new Date());
        BeanUtils.updateProperties(saveVO, entity);
        remote(userId, saveVO);
        return updateById(entity);
    }

    @GlobalTransactional(name = "cloud-admin-user-save-seata")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@NonNull Long id) {
        boolean b = super.removeById(id);
        deptHandler.removeByUserId(id);
        userPostHandler.removeByUserId(id);
        userRoleHandler.removeByUserId(id);
        return b;
    }

    @Override
    public List<SystemPermissionVO> getPermissionByUserId(@NonNull Long id) {
        List<Long> roleIds = userRoleHandler.getRoleIdByUserId(id);
        //获取角色权限
        return rolePermissionHandler.getPermission(roleIds);
    }

    @Override
    public List<SystemRoleVO> getRoleInfoByUserId(@NonNull Long userId) {
        return null;
    }

    /**
     * <p>
     * 远程调用
     * </p>
     *
     * @param userId 用户id
     * @param saveVO 用户信息
     */
    private void remote(Long userId, UserSaveVO saveVO) {
        if (!Objects.isNull(saveVO.getDeptId())) {
            remoteSaveDeptByUserId(Lists.newArrayList(saveVO.getDeptId()), userId);
        }
        if (!CollectionUtils.isEmpty(saveVO.getPosts())) {
            remoteSavePostByUserId(saveVO.getPosts(), userId);
        }
        if (!CollectionUtils.isEmpty(saveVO.getRoles())) {
            remoteSaveRole(saveVO.getRoles(), userId);
        }
    }

    private void verify(SystemUserVO saveVO, Boolean isUpdate) {
        //参数校验
        if (Objects.isNull(saveVO)) {
            throw new BusinessException("参数为空");
        }
        if (StringUtils.isBlank(saveVO.getUsername()) && StringUtils.isBlank(saveVO.getEmail())) {
            throw new BusinessException("用户账号或者email为空");
        }
        if (!isUpdate && StringUtils.isBlank(saveVO.getPassword())) {
            throw new BusinessException("用户密码为空");
        }
        //判断用户名或者邮箱是否已绑定
        List<SystemUserEntity> userEntity = getUserEntity(saveVO.getUsername());
        if (!CollectionUtils.isEmpty(userEntity)) {
            if (isUpdate) {
                if (userEntity.size() > 1) {
                    throw new BusinessException("用户账号或者邮箱已绑定");
                }
            } else {
                throw new BusinessException("用户账号或者邮箱已绑定");
            }
        }
        userEntity = getUserEntity(saveVO.getEmail());
        if (!CollectionUtils.isEmpty(userEntity)) {
            if (isUpdate) {
                if (userEntity.size() > 1) {
                    throw new BusinessException("用户账号或者邮箱已绑定");
                }
            } else {

                throw new BusinessException("用户账号或者邮箱已绑定");
            }
        }
    }

    /**
     * 远程调用组织用户绑定
     *
     * @param deptId 组织id
     * @param userId 用户id
     */
    private void remoteSaveDeptByUserId(@NonNull List<Long> deptId, @NonNull Long userId) {
        deptHandler.bindingDeptByUserId(userId, deptId);
    }

    /**
     * <p>
     * 远程调用岗位用户绑定
     * </p>
     *
     * @param postIds 岗位
     * @param userId  用户
     */
    private void remoteSavePostByUserId(@NonNull List<Long> postIds, @NonNull Long userId) {
        userPostHandler.bindingPostByUserId(userId, postIds);
    }

    /**
     * 绑定角色
     *
     * @param roleIds 角色id
     * @param userId  用户id
     */
    private void remoteSaveRole(@NonNull List<Long> roleIds, @NonNull Long userId) {
        userRoleHandler.bindingRoleByUserId(userId, roleIds);
    }

    /**
     * <p>
     * 根据用户账号或者email得到用户
     * </p>
     *
     * @param login 账号/email
     * @return 用户
     */
    private List<SystemUserEntity> getUserEntity(String login) {
        if (StringUtils.isBlank(login)) {
            return null;
        }
        SystemUserEntity entity = new SystemUserEntity();
        QueryWrapper<SystemUserEntity> queryWrapper = new QueryWrapper<>(entity.setUsername(login));
        List<SystemUserEntity> userEntity = list(queryWrapper);
        if (!Objects.isNull(userEntity)) {
            return userEntity;
        }
        entity.setUsername(null);
        entity.setEmail(login);
        queryWrapper.setEntity(entity);
        userEntity = list(queryWrapper);
        if (!Objects.isNull(userEntity)) {
            return userEntity;
        }
        return null;
    }


    /**
     * <p>
     * 用户密码加密
     * </p>
     *
     * @param password 密码文明
     * @return 加密后密码
     */
    private String passwordEncode(String password) {
        return passwordEncoder.encode(password);
    }
}
