package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.feign.IRemotePermissionMenu;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemotePermissionMenuFallback implements IRemotePermissionMenu {
    private Throwable throwable;

    public RemotePermissionMenuFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson getMenuByPermission(List<Long> permissionIds) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "获取菜单id失败,fallback message:" + throwable.getMessage());
    }
}
