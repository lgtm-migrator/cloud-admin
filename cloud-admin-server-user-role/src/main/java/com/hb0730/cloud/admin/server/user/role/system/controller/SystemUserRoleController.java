package com.hb0730.cloud.admin.server.user.role.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.user.role.feign.IRemoteRole;
import com.hb0730.cloud.admin.server.user.role.feign.IRemoteUser;
import com.hb0730.cloud.admin.server.user.role.system.model.entity.SystemUserRoleEntity;
import com.hb0730.cloud.admin.server.user.role.system.model.vo.SystemUserRoleVO;
import com.hb0730.cloud.admin.server.user.role.system.service.ISystemUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_ROLE_SERVER_REQUEST;

/**
 * <p>
 * 用户角色  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@RestController
@RequestMapping(USER_ROLE_SERVER_REQUEST)
public class SystemUserRoleController extends AbstractBaseController<SystemUserRoleVO> {


    @Autowired
    private IRemoteUser remoteUser;
    @Autowired
    private IRemoteRole remoteRole;

    @Autowired
    private ISystemUserRoleService systemUserRoleService;

    @Override
    public ResultJson save(SystemUserRoleVO target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemUserRoleVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
        return null;
    }

    /**
     * <p>
     * 用户角色绑定
     * </p>
     *
     * @return 是否成功
     */
    @GetMapping("/banding/{userId}/{roleId}")
    @Deprecated
    public ResultJson binding(@PathVariable Long userId, @PathVariable Long roleId) {
        //查询用户与角色id是否存在
        ResultJson result = remoteUser.findUserById(userId);
        result = verification(result, "根据id" + userId + "获取用户为空");
        if (!Objects.isNull(result)) {
            return result;
        }
        result = remoteRole.findRoleById(roleId);
        result = verification(result, "根据id" + roleId + "获取角色为空");
        if (!Objects.isNull(result)) {
            return result;
        }
        //查询用户是否重复绑定
        if (!getUserRoleByUserIdAndRoleId(userId, roleId)) {
            return ResponseResult.resultFall("用户重复绑定");
        }
        SystemUserRoleEntity entity = new SystemUserRoleEntity();
        entity.setRoleId(roleId);
        entity.setUserId(userId);
        UserDetail currentUser = null;
        try {
            currentUser = getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.resultSuccess("获取当前用户失败，请重新登录");
        }
        if (Objects.isNull(currentUser)) {
            return ResponseResult.resultSuccess("获取当前用户失败，请重新登录");
        }
        entity.setCreateTime(new Date());
        entity.setCreateUserId(currentUser.getUserId());
        //创建时间
        systemUserRoleService.save(entity);

        return ResponseResult.resultSuccess("用户角色绑定成功");
    }

    /**
     * <p>
     * 用户角色解绑
     * </p>
     *
     * @return 是否成功
     */
    @GetMapping("/unBanding/{userId}/{roleId}")
    @Deprecated
    public ResultJson unBanding(@PathVariable long userId, @PathVariable long roleId) {
        SystemUserRoleEntity entity = new SystemUserRoleEntity();
        entity.setUserId(userId);
        entity.setRoleId(roleId);
        entity.setIsEnabled(0);
        entity.setIsDelete(1);
        UpdateWrapper<SystemUserRoleEntity> updateWrapper = new UpdateWrapper<>(entity);
        systemUserRoleService.update(updateWrapper);
        return ResponseResult.resultSuccess("解绑成功");
    }

    /**
     * <p>
     * 根据用户获取角色
     * </p>
     *
     * @param userId 用户id
     * @return 角色
     */
    @GetMapping("/getRole/user/{userId}")
    @Deprecated
    public ResultJson getRoleByUserId(@PathVariable Long userId) {
        return remoteRole.findRoleByUserId(userId);
    }

    /**
     * <p>
     * 根据角色id获取用户id
     * </p>
     *
     * @return 角色
     */
    @GetMapping("/getRole/role/{roleId}")
    @Deprecated
    public ResultJson getRoleByRoleId(@PathVariable Long roleId) {
        return remoteRole.findRoleById(roleId);
    }


    /**
     * <p>
     * 根据用户id获取岗位id
     * </p>
     *
     * @param userId 用户id
     * @return 岗位id
     */
    @GetMapping("/getRoleId/{userId}")
    public ResultJson getPostByUserId(@PathVariable Long userId) {
        SystemUserRoleEntity entity = new SystemUserRoleEntity();
        entity.setIsEnabled(1);
        QueryWrapper<SystemUserRoleEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemUserRoleEntity> list = systemUserRoleService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return ResponseResult.resultSuccess(list);
        }
        List<Long> depts = list.parallelStream().map(SystemUserRoleEntity::getRoleId).collect(Collectors.toList());
        return ResponseResult.resultSuccess(depts);
    }

    /**
     * <p>
     * 根据用户信息绑定组织
     * </p>
     *
     * @param userId  用户id
     * @param postIds 岗位id
     * @return 是否成功
     */
    @PostMapping("/bindingRoleId/{userId}")
    public ResultJson bindingPostByUserId(@PathVariable Long userId, @RequestBody List<Long> postIds) {
        systemUserRoleService.bindingRoleByUserId(userId, postIds, getCurrentUser());
        return ResponseResult.resultSuccess("绑定成功");
    }

    /**
     * <p>
     * 查询相同用户是否绑定相同角色
     * </P>
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 存在false, 不存在true
     */
    private boolean getUserRoleByUserIdAndRoleId(@NonNull Long userId, @NonNull Long roleId) {
        Assert.notNull(userId, "用户id为空");
        Assert.notNull(roleId, "角色id为空");
        SystemUserRoleEntity entity = new SystemUserRoleEntity();
        entity.setUserId(userId);
        entity.setRoleId(roleId);
        QueryWrapper<SystemUserRoleEntity> queryWrapper = new QueryWrapper<>(entity);
        SystemUserRoleEntity result = systemUserRoleService.getOne(queryWrapper);
        return Objects.isNull(result);

    }

    /**
     * <p>
     * 校验
     * </p>
     *
     * @param result  返回结果
     * @param message 提示信息
     * @return 是否正确
     */
    private ResultJson verification(ResultJson result, String message) {
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            return ResponseResult.resultFall(result.getData());
        } else {
            if (Objects.isNull(result.getData())) {
                return ResponseResult.resultFall(message);
            }
        }
        return null;
    }
}

