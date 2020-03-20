package com.hb0730.cloud.admin.api.feign.permission.menu.remote.fallback;

import com.hb0730.cloud.admin.api.feign.permission.menu.model.vo.SystemPermissionMenuVO;
import com.hb0730.cloud.admin.api.feign.permission.menu.remote.IRemotePermissionMenu;
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
public class RemotePermissionMenuFallback implements IRemotePermissionMenu {
    private Throwable throwable;

    public RemotePermissionMenuFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson getMenuByPermission(List<Long> permissionIds) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据权限获取菜单失败，fallback message:" + throwable.getMessage());
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
