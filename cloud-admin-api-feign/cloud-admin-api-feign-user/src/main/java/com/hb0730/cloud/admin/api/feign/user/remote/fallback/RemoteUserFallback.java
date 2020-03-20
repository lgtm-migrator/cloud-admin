package com.hb0730.cloud.admin.api.feign.user.remote.fallback;

import com.hb0730.cloud.admin.api.feign.user.remote.IRemoteUser;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemoteUserFallback implements IRemoteUser {
    private Throwable throwable;

    public RemoteUserFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson findUserById(Long id) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据用户id获取用户失败 fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson findUserByUserName(String login) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据用户账号获取用户失败 fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson getPermissionByUserId(Long userId) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据用户获取权限失败, fallback message:" + throwable.getMessage());
    }
}
