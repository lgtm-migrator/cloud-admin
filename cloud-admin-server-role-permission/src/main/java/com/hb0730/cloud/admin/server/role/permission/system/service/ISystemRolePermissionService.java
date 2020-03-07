package com.hb0730.cloud.admin.server.role.permission.system.service;

import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.role.permission.system.model.entity.SystemRolePermissionEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
public interface ISystemRolePermissionService extends IService<SystemRolePermissionEntity> {

    /**
     * <p>
     * 保存
     * </P>
     *
     * @param id            角色id
     * @param permissionIds 权限id集
     * @param userDetail    当前用户
     * @return 是否成功
     */
    boolean save(Long id, List<Long> permissionIds, UserDetail userDetail);
}
