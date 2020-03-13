package com.hb0730.cloud.admin.server.permission.menu.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.permission.menu.feign.IRemotePermission;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemotePermissionFallback implements IRemotePermission {
    private Throwable throwable;

    public RemotePermissionFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson getPermissionByIds(List<Long> id) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据id集获取权限失败,fallback:"+throwable.getClass().getSimpleName());
    }
}
