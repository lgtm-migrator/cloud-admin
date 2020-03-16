package com.hb0730.clou.admin.api.user.role;

import com.hb0730.cloud.admin.common.web.response.ResultJson;

import java.util.List;

/**
 * <p>
 * 远程调用用户角色
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IRemoteUserRole {
    /**
     * <p>
     * 根据用户id获取岗位id
     * </p>
     *
     * @param userId 用户id
     * @return 岗位id
     */
    ResultJson getRoleByUserId(Long userId);

    /**
     * <p>
     * 根据用户信息绑定组织
     * </p>
     *
     * @param userId  用户id
     * @param postIds 岗位id
     * @return 是否成功
     */
    ResultJson bindingRoleByUserId(Long userId, List<Long> postIds);
}
