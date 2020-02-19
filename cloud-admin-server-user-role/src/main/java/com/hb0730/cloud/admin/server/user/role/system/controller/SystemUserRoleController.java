package com.hb0730.cloud.admin.server.user.role.system.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.role.feign.IRemoteRole;
import com.hb0730.cloud.admin.server.user.role.feign.IRemoteUser;
import com.hb0730.cloud.admin.server.user.role.system.model.entity.SystemUserRoleEntity;
import com.hb0730.cloud.admin.server.user.role.system.service.ISystemUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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
public class SystemUserRoleController extends AbstractBaseController<SystemUserRoleEntity> {


    @Autowired
    private IRemoteUser remoteUser;
    @Autowired
    private IRemoteRole remoteRole;

    @Autowired
    private ISystemUserRoleService systemUserRoleService;

    @Override
    public ResultJson save(SystemUserRoleEntity target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemUserRoleEntity target) {
        return null;
    }

    @Override
    public ResultJson gitObject(Object id) {
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
    public ResultJson binding(@PathVariable Long userId, @PathVariable Long roleId) {
        //查询用户与角色id是否存在
        ResultJson result = remoteUser.findUserById(userId);
        result = verification(result, "根据id" + userId + "获取用户为空");
        if (!Objects.isNull(result)) {
            return result;
        }
        result = remoteRole.findRoleByUserId(roleId);
        if (!Objects.isNull(result)) {
            return result;
        }
        SystemUserRoleEntity entity = new SystemUserRoleEntity();
        entity.setRoleId(roleId);
        entity.setUserId(userId);
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
    public ResultJson unBanding(@PathVariable long userId, @PathVariable long roleId) {
        SystemUserRoleEntity entity = new SystemUserRoleEntity();
        entity.setUserId(userId);
        entity.setRoleId(roleId);
        entity.setIsEnabled(0);
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
    public ResultJson getRoleByRoleId(@PathVariable Long roleId) {
        return remoteRole.findRoleById(roleId);
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

    /**
     * <p>
     * 获取认证用户
     * </p>
     *
     * @return 用户
     */
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

