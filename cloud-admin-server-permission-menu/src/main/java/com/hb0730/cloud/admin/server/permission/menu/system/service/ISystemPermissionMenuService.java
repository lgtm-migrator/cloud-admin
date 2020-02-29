package com.hb0730.cloud.admin.server.permission.menu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.cloud.admin.server.permission.menu.system.model.entity.SystemPermissionMenuEntity;
import com.hb0730.cloud.admin.server.permission.menu.system.model.vo.PermissionMenuVO;
import com.hb0730.cloud.admin.server.permission.menu.system.model.vo.SystemPermissionMenuVO;

import java.util.List;

/**
 * <p>
 * 菜单权限  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
public interface ISystemPermissionMenuService extends IService<SystemPermissionMenuEntity> {
    /**
     * 根据菜单id获取权限信息
     *
     * @param vo 参数
     * @return 权限信息
     */
    List<PermissionMenuVO> getPermissionMenuByMenuId(SystemPermissionMenuVO vo);

    /**
     * <p>
     * 解除绑定
     * </p>
     *
     * @param permissionId 权限id
     * @param menuId 菜单id
     * @return 是否成功
     */
    boolean unBinding(Long permissionId, Long menuId);
}
