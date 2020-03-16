package com.hb0730.cloud.admin.server.user.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.exception.BusinessException;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.user.feign.IRemoteUserDept;
import com.hb0730.cloud.admin.server.user.feign.IRemoteUserPost;
import com.hb0730.cloud.admin.server.user.system.mapper.SystemUserMapper;
import com.hb0730.cloud.admin.server.user.system.model.entity.SystemUserEntity;
import com.hb0730.cloud.admin.server.user.system.model.vo.UserSaveVO;
import com.hb0730.cloud.admin.server.user.system.service.ISystemUserService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    private IRemoteUserDept remoteUserDept;
    @Autowired
    private IRemoteUserPost remoteUserPost;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional(name = "cloud-admin-user-save-seata")
    public boolean save(UserSaveVO saveVO, UserDetail userDetail) {
        //参数校验
        if (Objects.isNull(saveVO)) {
            throw new BusinessException("参数为空");
        }
        if (StringUtils.isBlank(saveVO.getUsername()) && StringUtils.isBlank(saveVO.getEmail())) {
            throw new BusinessException("用户账号或者email为空");
        }
        if (StringUtils.isBlank(saveVO.getPassword())) {
            throw new BusinessException("用户密码为空");
        }
        //判断用户名或者邮箱是否已绑定
        SystemUserEntity userEntity = getUserEntity(saveVO.getUsername());
        if (!Objects.isNull(userEntity)) {
            throw new BusinessException("用户账号或者邮箱已绑定");
        }
        userEntity = getUserEntity(saveVO.getEmail());
        if (!Objects.isNull(userEntity)) {
            throw new BusinessException("用户账号或者邮箱已绑定");
        }
        //用户加密
        String password = passwordEncode(saveVO.getPassword());
        saveVO.setPassword(password);
        saveVO.setCreateTime(new Date());
        saveVO.setCreateUserId(userDetail.getUserId());
        SystemUserEntity entity = BeanUtils.transformFrom(saveVO, SystemUserEntity.class);
        boolean save = save(entity);
        Long deptId = saveVO.getDeptId();
        assert entity != null;
        if (!Objects.isNull(deptId)) {
            remoteSaveDeptByUserId(Lists.newArrayList(deptId), entity.getId());
        }
        if (!CollectionUtils.isEmpty(saveVO.getPosts())) {
            remoteSavePostByUserId(saveVO.getPosts(), entity.getId());
        }
        return false;
    }

    /**
     * 远程调用组织用户绑定
     *
     * @param deptId 组织id
     * @param userId 用户id
     */
    private void remoteSaveDeptByUserId(@NonNull List<Long> deptId, @NonNull Long userId) {
        ResultJson result = remoteUserDept.bindingDeptByUserId(userId, deptId);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            throw new BusinessException(result.getData().toString());
        }
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
        ResultJson result = remoteUserPost.bindingPostByUserId(userId, postIds);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            throw new BusinessException(result.getData().toString());
        }
    }

    /**
     * <p>
     * 根据用户账号或者email得到用户
     * </p>
     *
     * @param login 账号/email
     * @return 用户
     */
    private SystemUserEntity getUserEntity(String login) {
        SystemUserEntity entity = new SystemUserEntity();
        QueryWrapper<SystemUserEntity> queryWrapper = new QueryWrapper<>(entity.setUsername(login));
        SystemUserEntity userEntity = getOne(queryWrapper);
        if (!Objects.isNull(userEntity)) {
            return userEntity;
        }
        entity.setUsername(null);
        entity.setEmail(login);
        queryWrapper.setEntity(entity);
        userEntity = getOne(queryWrapper);
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
