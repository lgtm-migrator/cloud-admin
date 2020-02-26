package com.hb0730.cloud.admin.server.permission.system.service;

import com.hb0730.cloud.admin.server.permission.system.model.entity.SystemPermissionEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.cloud.admin.server.permission.system.model.vo.PermissionMenuVO;

/**
 * <p>
 * 系统权限  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
public interface ISystemPermissionService extends IService<SystemPermissionEntity> {

    /**
     * <p>
     * 保存权限及菜单
     * </p>
     *
     * @param permissionMenuVO 权限菜单
     * @return 是否成功
     */
    boolean save(PermissionMenuVO permissionMenuVO);
}
