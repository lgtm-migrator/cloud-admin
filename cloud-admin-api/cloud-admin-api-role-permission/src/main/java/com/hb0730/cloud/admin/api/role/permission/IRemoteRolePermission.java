package com.hb0730.cloud.admin.api.role.permission;

import com.hb0730.cloud.admin.common.web.response.ResultJson;

import java.util.List;

/**
 * <p>
 * 远程调用角色权限
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IRemoteRolePermission {

    /**
     * 根据角色获取权限
     *
     * @param roleIds 角色id
     * @return 权限
     */
    ResultJson getPermissionIdByRoleIds(List<Long> roleIds);
}
