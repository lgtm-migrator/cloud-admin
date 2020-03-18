package com.hb0730.cloud.admin.server.user.handler;

import com.hb0730.cloud.admin.common.exception.BusinessException;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.server.user.feign.IRemoteUserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class UserPostHandler {
    @Autowired
    private IRemoteUserPost remoteUserPost;

    /**
     * <p>
     * 用户岗位绑定
     * </p>
     *
     * @param userId  用户id
     * @param postIds 岗位id
     */
    public void bindingPostByUserId(@NonNull Long userId, @NonNull List<Long> postIds) {
        ResultJson result = remoteUserPost.bindingPostByUserId(userId, postIds);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            throw new BusinessException(result.getData().toString());
        }
    }

    /**
     * <p>
     * 解除绑定
     * </p>
     *
     * @param userId 用户id
     */
    public void removeByUserId(@NonNull Long userId) {
        ResultJson resultJson = remoteUserPost.removeByUserId(userId);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(resultJson.getErrCode())) {
            throw new BusinessException(resultJson.getData().toString());
        }
    }

    /**
     * <p>
     * 根据用户id获取岗位id
     * </p>
     *
     * @param userId 用户id
     * @return 岗位id
     */
    public List<Long> getPostIdByUserId(@NonNull Long userId) {
        ResultJson resultUserPosts = remoteUserPost.getPostByUserId(userId);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(resultUserPosts.getErrCode())) {
            throw new BusinessException(resultUserPosts.getData().toString());
        }
        return GsonUtils.json2List(GsonUtils.json2String(resultUserPosts.getData()), Long.class);
    }
}
