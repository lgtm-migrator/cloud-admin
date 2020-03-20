package com.hb0730.cloud.admin.server.permission.system.service.impl;

import com.hb0730.cloud.admin.api.feign.permission.menu.handler.PermissionMenuHandler;
import com.hb0730.cloud.admin.api.feign.permission.menu.model.vo.SystemPermissionMenuVO;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.permission.system.mapper.SystemPermissionMapper;
import com.hb0730.cloud.admin.server.permission.system.model.entity.SystemPermissionEntity;
import com.hb0730.cloud.admin.server.permission.system.model.vo.PermissionMenuVO;
import com.hb0730.cloud.admin.server.permission.system.service.ISystemPermissionService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 系统权限  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@Service
public class SystemPermissionServiceImpl extends BaseServiceImpl<SystemPermissionMapper, SystemPermissionEntity> implements ISystemPermissionService {
    @Autowired
    private PermissionMenuHandler permissionMenuHandler;

    @Override
    @GlobalTransactional(name = "cloud-admin-permission-seata")
    @Transactional(rollbackFor = Exception.class)
    public boolean save(PermissionMenuVO permissionMenuVO) {
        //新增数据
        SystemPermissionEntity entity = BeanUtils.transformFrom(permissionMenuVO, SystemPermissionEntity.class);
        boolean save = save(entity);
        SystemPermissionMenuVO vo = new SystemPermissionMenuVO();
        vo.setMenuId(permissionMenuVO.getMenuId());
        assert entity != null;
        vo.setPermissionId(entity.getId());
        sendSavePermissionMenu(vo);
        return save;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional(name = "cloud-admin-permission-seata")
    public boolean unBinding(Long permissionId, Long menuId) {
        remoteUnBinding(permissionId, menuId);
        return removeById(permissionId);
    }

    @Override
    public boolean save(SystemPermissionEntity entity) {
        return super.save(entity);
    }

    /**
     * 远程调用解绑
     *
     * @param permissionId 权限id
     * @param menuId       菜单id
     */
    private void remoteUnBinding(Long permissionId, Long menuId) {
        if (Objects.isNull(permissionId)) {
            throw new NullPointerException("权限id为空");
        }
        if (Objects.isNull(menuId)) {
            throw new NullPointerException("菜单id为空");
        }
        permissionMenuHandler.unBinding(permissionId, menuId);
    }

    /**
     * <p>
     * 远程调用
     * </p>
     *
     * @param vo 菜单权限信息
     */
    private void sendSavePermissionMenu(SystemPermissionMenuVO vo) {
        if (Objects.isNull(vo)) {
            throw new NullPointerException("菜单权限为空");
        }
        Long permissionId = vo.getPermissionId();
        if (Objects.isNull(permissionId)) {
            throw new NullPointerException("权限id为空");
        }
        Long menuId = vo.getMenuId();
        if (Objects.isNull(menuId)) {
            throw new NullPointerException("菜单id为空");
        }
        permissionMenuHandler.save(vo);
    }
}
