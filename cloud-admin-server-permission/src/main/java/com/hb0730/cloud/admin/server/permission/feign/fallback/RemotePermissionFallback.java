package com.hb0730.cloud.admin.server.permission.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.permission.feign.IRemotePermissionMenu;
import com.hb0730.cloud.admin.server.permission.system.model.vo.SystemPermissionMenuVO;
import feign.hystrix.FallbackFactory;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemotePermissionFallback implements IRemotePermissionMenu {
    private Throwable throwable;

    public RemotePermissionFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson save(SystemPermissionMenuVO vo) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "保存权限菜单失败 fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson unBinding(Long permissionId, Long menuId) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "解除权限与菜单失败 fallback message:" + throwable.getMessage());
    }
}
