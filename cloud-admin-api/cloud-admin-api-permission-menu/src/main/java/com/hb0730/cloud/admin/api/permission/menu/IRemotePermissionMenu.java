package com.hb0730.cloud.admin.api.permission.menu;

import com.hb0730.cloud.admin.common.web.response.ResultJson;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IRemotePermissionMenu {
    /**
     * <p>
     * 根据权限id获取菜单id
     * </p>
     *
     * @param permissionIds 权限id
     * @return 根据权限获取所有的菜单id(非树形结构)
     */
    ResultJson getMenuByPermission(List<Long> permissionIds);
}
