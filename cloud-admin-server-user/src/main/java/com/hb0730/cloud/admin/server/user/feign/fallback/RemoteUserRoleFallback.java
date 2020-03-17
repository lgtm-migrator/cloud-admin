package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.feign.IRemoteUserRole;

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
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "获取用户角色失败,fallback:" + throwable.getClass().getSimpleName() + ",message" + throwable.getMessage());
    }

    @Override
    public ResultJson bindingRoleByUserId(Long userId, List<Long> postIds) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "绑定用户角色失败,fallback:" + throwable.getClass().getSimpleName() + ",message" + throwable.getMessage());
    }
}
