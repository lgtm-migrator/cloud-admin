package com.hb0730.cloud.admin.api.feign.role.permission.remote.fallback;

import com.hb0730.cloud.admin.api.feign.role.permission.remote.IRemoteRolePermission;
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
public class RemoteRolePermissionFallback implements IRemoteRolePermission {
    private Throwable throwable;

    public RemoteRolePermissionFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson getPermissionIdByRoleIds(List<Long> roleIds) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "获取用户权限失败, fallback message:" + throwable.getMessage());
    }
}
