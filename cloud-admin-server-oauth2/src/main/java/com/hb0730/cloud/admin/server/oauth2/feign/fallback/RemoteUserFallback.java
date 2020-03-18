package com.hb0730.cloud.admin.server.oauth2.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.oauth2.feign.IRemoteUser;

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
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据id获取用户失败, fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson findUserByUserName(String login) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据账号获取用户失败, fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson getPermissionByUserId(Long userId) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据用户获取权限失败, fallback message:" + throwable.getMessage());
    }
}
