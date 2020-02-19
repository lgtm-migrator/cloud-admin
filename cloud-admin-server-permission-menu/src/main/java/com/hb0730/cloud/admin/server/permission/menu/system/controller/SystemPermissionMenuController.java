package com.hb0730.cloud.admin.server.permission.menu.system.controller;


import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.server.permission.menu.system.model.entity.SystemPermissionMenuEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.PERMISSION_MENU_SERVER_REQUEST;

/**
 * <p>
 * 菜单权限  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@RestController
@RequestMapping(PERMISSION_MENU_SERVER_REQUEST)
public class SystemPermissionMenuController extends AbstractBaseController<SystemPermissionMenuEntity> {

    @Override
    public ResultJson save(SystemPermissionMenuEntity target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemPermissionMenuEntity target) {
        return null;
    }

    @Override
    public ResultJson gitObject(Object id) {
        return null;
    }
}

