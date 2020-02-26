package com.hb0730.cloud.admin.server.permission.system.service.impl;

import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.server.permission.system.model.entity.SystemPermissionEntity;
import com.hb0730.cloud.admin.server.permission.system.mapper.SystemPermissionMapper;
import com.hb0730.cloud.admin.server.permission.system.service.ISystemPermissionService;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.permission.system.model.vo.PermissionMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private SystemPermissionMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(PermissionMenuVO permissionMenuVO) {
        //新增数据
        SystemPermissionEntity entity = BeanUtils.transformFrom(permissionMenuVO, SystemPermissionEntity.class);
        boolean save = save(entity);
        return save;
    }

    @Override
    public boolean save(SystemPermissionEntity entity) {
        return super.save(entity);
    }
}
