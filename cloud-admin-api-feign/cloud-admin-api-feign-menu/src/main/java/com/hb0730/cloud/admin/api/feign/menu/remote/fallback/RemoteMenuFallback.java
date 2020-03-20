package com.hb0730.cloud.admin.api.feign.menu.remote.fallback;

import com.hb0730.cloud.admin.api.feign.menu.remote.IRemoteMenu;
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
public class RemoteMenuFallback implements IRemoteMenu {
    private Throwable throwable;

    public RemoteMenuFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson getMenuById(Long id) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "获取菜单失败，fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson getMenusByParentId(Long parentId) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据父id获取菜单失败，fallback message:" + throwable.getMessage());
    }
}
