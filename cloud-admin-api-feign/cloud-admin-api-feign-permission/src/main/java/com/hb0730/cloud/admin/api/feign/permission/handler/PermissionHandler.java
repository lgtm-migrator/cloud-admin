package com.hb0730.cloud.admin.api.feign.permission.handler;

import com.hb0730.cloud.admin.api.feign.permission.remote.IRemotePermission;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.JsonConvertBeanUtils;
import com.hb0730.cloud.admin.commons.permission.model.vo.SystemPermissionVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class PermissionHandler {
    private IRemotePermission remotePermission;

    /**
     * <p>
     * 根据权限id获取权限信息
     * </p>
     *
     * @param permissionIds 权限id
     * @return 权限信息
     */
    public List<SystemPermissionVO> getPermissionByIds(List<Long> permissionIds) {
        ResultJson result = remotePermission.getPermissionByIds(permissionIds);
        return JsonConvertBeanUtils.convertList(result, SystemPermissionVO.class);
    }
}
