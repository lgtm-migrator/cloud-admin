package com.hb0730.cloud.admin.server.user.handler;

import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.exception.BusinessException;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.commons.permission.model.vo.SystemPermissionVO;
import com.hb0730.cloud.admin.server.user.feign.IRemotePermission;
import com.hb0730.cloud.admin.server.user.feign.IRemoteRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 角色权限
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RolePermissionHandler {
    @Autowired
    private IRemoteRolePermission remoteRolePermission;
    @Autowired
    private IRemotePermission remotePermission;

    /**
     * <p>
     * 根据角色id获取权限信息
     * </p>
     *
     * @param roleIds 角色集合
     */
    public List<SystemPermissionVO> getPermission(List<Long> roleIds) {
        List<Long> permissionIds = getPermissionIds(roleIds);
        ResultJson resultPermission = remotePermission.getPermissionByIds(permissionIds);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(resultPermission.getStatusCode())) {
            throw new BusinessException(resultPermission.getData().toString());
        }
        return GsonUtils.json2List(GsonUtils.json2String(resultPermission.getData()), SystemPermissionVO.class);
    }

    /**
     * <p>
     * 根据角色获取权限id
     * </p>
     *
     * @param roleIds 角色id
     * @return 权限id
     */
    public List<Long> getPermissionIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        ResultJson resultPermissionIds = remoteRolePermission.getPermissionIdByRoleIds(roleIds);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(resultPermissionIds.getStatusCode())) {
            throw new BusinessException(resultPermissionIds.getData().toString());
        }
        return GsonUtils.json2List(GsonUtils.json2String(resultPermissionIds.getData()), Long.class);
    }
}
