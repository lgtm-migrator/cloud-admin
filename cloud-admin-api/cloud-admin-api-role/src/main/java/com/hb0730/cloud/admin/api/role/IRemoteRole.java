package com.hb0730.cloud.admin.api.role;

import com.hb0730.cloud.admin.common.web.response.ResultJson;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IRemoteRole {
    /**
     * <p>
     * 根据id获取角色信息
     * </P>
     *
     * @param ids 角色id
     * @return 角色信息
     */
    ResultJson getRolesByIds(List<Long> ids);
}
