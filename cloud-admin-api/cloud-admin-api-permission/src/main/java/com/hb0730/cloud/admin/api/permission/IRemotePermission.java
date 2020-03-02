package com.hb0730.cloud.admin.api.permission;

import com.hb0730.cloud.admin.common.web.response.ResultJson;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IRemotePermission {


    /**
     * <p>
     * 根据ids获取权限
     * </p>
     *
     * @param id ids
     * @return 权限级
     */
    ResultJson getPermissionByIds(List<Long> id);

}
