package com.hb0730.cloud.admin.api.feign.permission.menu.handler;

import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.api.feign.permission.menu.model.vo.SystemPermissionMenuVO;
import com.hb0730.cloud.admin.api.feign.permission.menu.remote.IRemotePermissionMenu;
import com.hb0730.cloud.admin.common.web.exception.BusinessException;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.JsonConvertBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class PermissionMenuHandler {
    @Autowired
    private IRemotePermissionMenu remotePermissionMenu;

    /**
     * <p>
     * 根据权限id获取菜单id
     * </P>
     *
     * @param ids 权限id
     * @return 菜单id
     */
    public List<Long> getMenuIdByPermissionId(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        ResultJson result = remotePermissionMenu.getMenuByPermission(ids);
        return JsonConvertBeanUtils.convertList(result, Long.class);
    }

    /**
     * <p>
     * 菜单权限解绑
     * </p>
     *
     * @param permissionId 权限id
     * @param menuId       菜单id
     */
    public void unBinding(Long permissionId, Long menuId) {
        if (Objects.isNull(permissionId) || Objects.isNull(menuId)) {
            return;
        }
        ResultJson result = remotePermissionMenu.unBinding(permissionId, menuId);
        String code = result.getStatusCode();
        if (!CodeStatusEnum.SUCCESS.getCode().equals(code)) {
            throw new BusinessException(result.getData().toString());
        }
    }

    /**
     * <p>
     * 新增权限菜单绑定
     * </p>
     *
     * @param vo 权限菜单信息
     */
    public void save(SystemPermissionMenuVO vo) {
        if (Objects.isNull(vo)) {
            return;
        }
        ResultJson result = remotePermissionMenu.save(vo);
        String code = result.getStatusCode();
        if (!CodeStatusEnum.SUCCESS.getCode().equals(code)) {
            throw new BusinessException(result.getData().toString());
        }
    }
}
