package com.hb0730.cloud.admin.api.feign.permission.menu.remote;

import com.hb0730.cloud.admin.api.feign.permission.menu.model.vo.SystemPermissionMenuVO;
import com.hb0730.cloud.admin.api.feign.permission.menu.remote.fallback.RemotePermissionMenuFallbackFactory;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
public interface IRemotePermissionMenu {

    /**
     * <p>
     * 根据权限id获取菜单id
     * </P>
     *
     * @param permissionIds 权限id
     * @return 菜单id
     */
    @PostMapping("/menus")
    ResultJson getMenuByPermission(@RequestBody List<Long> permissionIds);

    /**
     * <p>
     * 新增绑定信息
     * </p>
     *
     * @param vo 菜单权限vo
     * @return 是否成功
     */
    @PostMapping("/save")
    ResultJson save(SystemPermissionMenuVO vo);

    /**
     * 解除绑定
     *
     * @param permissionId 权限id
     * @param menuId       菜单id
     * @return 是否成功
     */
    @GetMapping("/unBinding/{permissionId}/{menuId}")
    ResultJson unBinding(@PathVariable("permissionId") Long permissionId, @PathVariable("menuId") Long menuId);
}
