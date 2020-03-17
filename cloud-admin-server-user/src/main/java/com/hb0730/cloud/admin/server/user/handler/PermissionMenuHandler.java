package com.hb0730.cloud.admin.server.user.handler;

import com.hb0730.cloud.admin.common.exception.BusinessException;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.server.user.feign.IRemotePermissionMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 权限菜单信息
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class PermissionMenuHandler {
    @Autowired
    private RolePermissionHandler permissionHandler;
    @Autowired
    private IRemotePermissionMenu remotePermissionMenu;

    /**
     * <p>
     * 获取菜单
     * </p>
     */
    public void getPermissionMenu(List<Long> roleIds) {
        List<Long> permissionIds = permissionHandler.getPermissionIds(roleIds);
        if (!CollectionUtils.isEmpty(permissionIds)) {
            return;
        }
        ResultJson resultPermissionMenu = remotePermissionMenu.getMenuByPermission(permissionIds);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(resultPermissionMenu.getErrCode())) {
            throw new BusinessException(resultPermissionMenu.getData().toString());
        }
        List<Long> menuIds = GsonUtils.json2List(GsonUtils.json2String(resultPermissionMenu.getData()), Long.class);

    }
}
