package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.feign.IRemoteRolePermission;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemoteRolePermissionFallback implements IRemoteRolePermission {
    private Throwable throwable;

    public RemoteRolePermissionFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson getPermissionIdByRoleIds(List<Long> roleIds) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "获取用户权限失败, fallback message:" + throwable.getMessage());
    }
}
