package com.hb0730.cloud.admin.server.user.feign;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.server.user.feign.fallback.RemotePermissionMenuFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.PERMISSION_MENU_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.PERMISSION_MENU_SERVER;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = PERMISSION_MENU_SERVER, path = PERMISSION_MENU_SERVER_REQUEST, configuration = FeignConfiguration.class, fallbackFactory = RemotePermissionMenuFallbackFactory.class)
public interface IRemotePermissionMenu extends com.hb0730.cloud.admin.api.permission.menu.IRemotePermissionMenu {
    /**
     * <p>
     * 根据权限id获取菜单id
     * </p>
     *
     * @param permissionIds 权限id
     * @return 树形菜单
     */
    @PostMapping("/menus")
    @Override
    ResultJson getMenuByPermission(List<Long> permissionIds);
}
