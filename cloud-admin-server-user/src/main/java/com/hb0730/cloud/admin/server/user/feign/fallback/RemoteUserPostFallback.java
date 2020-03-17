package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.feign.IRemoteUserPost;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemoteUserPostFallback implements IRemoteUserPost {
    private Throwable throwable;

    public RemoteUserPostFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson getPostByUserId(Long userId) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "获取岗位失败,fallback:" + throwable.getClass().getSimpleName() + ",message:" + throwable.getMessage());
    }

    @Override
    public ResultJson bindingPostByUserId(Long userId, List<Long> postIds) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "绑定岗位失败,fallback:" + throwable.getClass().getSimpleName() + ",message:" + throwable.getMessage());
    }

    @Override
    public ResultJson removeByUserId(Long userId) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据用户删除岗位失败, fallback:" + throwable.getMessage());
    }
}
