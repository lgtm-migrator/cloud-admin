package com.hb0730.cloud.admin.server.user.handler;

import com.google.common.collect.Lists;
import com.hb0730.clou.admin.commons.model.role.SystemRoleVO;
import com.hb0730.cloud.admin.common.web.exception.BusinessException;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.JsonConvertBeanUtils;
import com.hb0730.cloud.admin.server.user.feign.IRemoteRole;
import com.hb0730.cloud.admin.server.user.feign.IRemoteUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class UserRoleHandler {
    @Autowired
    private IRemoteUserRole remoteUserRole;

    @Autowired
    private IRemoteRole remoteRole;

    /**
     * 根据用户id获取角色id
     *
     * @param userId 用户id
     * @return 角色id
     */
    public List<Long> getRoleIdByUserId(@NonNull Long userId) {
        ResultJson resultUserRole = remoteUserRole.getRoleByUserId(userId);
        return JsonConvertBeanUtils.convertList(resultUserRole, Long.class);
    }

    /**
     * <p>
     * 根据用户id获取角色信息
     * </P>
     *
     * @param userId 用户id
     * @return 角色信息
     */
    public List<SystemRoleVO> getRoleInfoByUserId(@NonNull Long userId) {
        List<Long> roleIds = getRoleIdByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        ResultJson result = remoteRole.getRolesByIds(roleIds);
        return JsonConvertBeanUtils.convertList(result, SystemRoleVO.class);
    }

    /**
     * <p>
     * 根据用户id解绑
     * </p>
     *
     * @param userId 用户id
     */
    public void removeByUserId(@NonNull Long userId) {
        ResultJson resultJson = remoteUserRole.removeByUserId(userId);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(resultJson.getStatusCode())) {
            throw new BusinessException(resultJson.getData().toString());
        }
    }

    /**
     * <p>
     * 用户与角色绑定
     * </P>
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    public void bindingRoleByUserId(@NonNull Long userId, @NonNull List<Long> roleId) {
        ResultJson result = remoteUserRole.bindingRoleByUserId(userId, roleId);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getStatusCode())) {
            throw new BusinessException(result.getData().toString());
        }
    }
}
