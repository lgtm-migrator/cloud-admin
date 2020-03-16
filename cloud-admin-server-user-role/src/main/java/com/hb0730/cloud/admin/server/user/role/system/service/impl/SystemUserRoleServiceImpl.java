package com.hb0730.cloud.admin.server.user.role.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.user.role.system.model.entity.SystemUserRoleEntity;
import com.hb0730.cloud.admin.server.user.role.system.mapper.SystemUserRoleMapper;
import com.hb0730.cloud.admin.server.user.role.system.service.ISystemUserRoleService;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@Service
public class SystemUserRoleServiceImpl extends BaseServiceImpl<SystemUserRoleMapper, SystemUserRoleEntity> implements ISystemUserRoleService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bindingRoleByUserId(@NonNull Long userId, @NonNull List<Long> roleIds, @NonNull UserDetail userDetail) {
        SystemUserRoleEntity entity = new SystemUserRoleEntity();
        entity.setUserId(userId);
        QueryWrapper<SystemUserRoleEntity> queryWrapper = new QueryWrapper<>(entity);
        remove(queryWrapper);
        List<SystemUserRoleEntity> entities = Lists.newArrayList();
        roleIds.parallelStream().forEach(roleId -> {
            SystemUserRoleEntity e1 = new SystemUserRoleEntity();
            e1.setUserId(userId);
            e1.setRoleId(roleId);
            e1.setIsEnabled(1);
            e1.setIsDelete(0);
            e1.setCreateTime(new Date());
            e1.setCreateUserId(userDetail.getUserId());
            e1.setVersion(1);
            entities.add(e1);
        });
        return saveBatch(entities);
    }
}
