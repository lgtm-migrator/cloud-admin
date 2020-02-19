package com.hb0730.cloud.admin.server.role.permission.system.controller;


import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.server.role.permission.system.model.entity.SystemRolePermissionEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.ROLE_PERMISSION_SERVER_REQUEST;

/**
 * <p>
 * 角色权限  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@RestController
@RequestMapping(ROLE_PERMISSION_SERVER_REQUEST)
public class SystemRolePermissionController extends AbstractBaseController<SystemRolePermissionEntity> {

    @Override
    public ResultJson save(SystemRolePermissionEntity target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemRolePermissionEntity target) {
        return null;
    }

    @Override
    public ResultJson gitObject(Object id) {
        return null;
    }
}

