package com.hb0730.cloud.admin.api.user.post;

import com.hb0730.cloud.admin.common.web.response.ResultJson;

import java.util.List;

/**
 * <p>
 * 远程调用用户岗位
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IRemoteUserPost {
    /**
     * 获取用户已绑定的组织id
     *
     * @param userId 用户id
     * @return 组织
     */
    ResultJson getPostByUserId(Long userId);

    /**
     * <p>
     * 根据用户信息绑定组织
     * </p>
     *
     * @param userId  用户id
     * @param postIds 岗位id
     * @return 是否成功
     */
    ResultJson bindingPostByUserId(Long userId, List<Long> postIds);
}
