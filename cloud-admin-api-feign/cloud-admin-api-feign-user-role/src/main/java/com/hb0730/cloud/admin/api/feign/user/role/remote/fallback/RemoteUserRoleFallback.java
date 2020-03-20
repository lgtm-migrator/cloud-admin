package com.hb0730.cloud.admin.api.feign.user.role.remote.fallback;

import com.hb0730.cloud.admin.api.feign.user.role.remote.IRemoteUserRole;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemoteUserRoleFallback implements IRemoteUserRole {
    private Throwable throwable;

    public RemoteUserRoleFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson getRoleByUserId(Long userId) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "获取用户角色失败(Feign熔断),fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson bindingRoleByUserId(Long userId, List<Long> postIds) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "绑定用户角色失败(Feign熔断),fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson removeByUserId(Long userId) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据用户删除角色失败(Feign熔断), fallback message:" + throwable.getMessage());
    }
}
