package com.hb0730.cloud.admin.server.menu.handler;

import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.exception.BusinessException;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.server.menu.feign.IRemotePermissionMenu;
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
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            throw new BusinessException(result.getData().toString());
        }
        if (Objects.isNull(result.getData())) {
            return Lists.newArrayList();
        }
        return GsonUtils.json2List(GsonUtils.json2String(result.getData()), Long.class);
    }
}
