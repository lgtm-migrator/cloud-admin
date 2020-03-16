package com.hb0730.cloud.admin.server.role.dept.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.role.dept.system.mapper.SystemRoleDeptMapper;
import com.hb0730.cloud.admin.server.role.dept.system.model.entity.SystemRoleDeptEntity;
import com.hb0730.cloud.admin.server.role.dept.system.service.ISystemRoleDeptService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色组织  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-16
 */
@Service
public class SystemRoleDeptServiceImpl extends BaseServiceImpl<SystemRoleDeptMapper, SystemRoleDeptEntity> implements ISystemRoleDeptService {

    @Override
    public List<Long> getDeptIdByRoleId(@NonNull Long roleId) {
        SystemRoleDeptEntity entity = new SystemRoleDeptEntity();
        entity.setRoleId(roleId);
        QueryWrapper<SystemRoleDeptEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemRoleDeptEntity> entityList = list(queryWrapper);
        if (CollectionUtils.isEmpty(entityList)) {
            return Lists.newArrayList();
        }
        return entityList.parallelStream().map(SystemRoleDeptEntity::getDeptId).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bindingDeptIds(@NonNull Long roleId, @NonNull List<Long> deptIds, @NonNull UserDetail userDetail) {
        SystemRoleDeptEntity entity = new SystemRoleDeptEntity();
        entity.setRoleId(roleId);
        QueryWrapper<SystemRoleDeptEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemRoleDeptEntity> entityList = list(queryWrapper);
        List<SystemRoleDeptEntity> list = Lists.newArrayList();
        if (CollectionUtils.isEmpty(entityList)) {
            deptIds.parallelStream().forEach(deptId -> getEntityList(deptId, roleId, list, userDetail));
            return saveBatch(list);
        }
        List<Long> entityDeptIds = entityList.parallelStream().map(SystemRoleDeptEntity::getDeptId).collect(Collectors.toList());
        deptIds.removeAll(entityDeptIds);
        if (CollectionUtils.isEmpty(deptIds)) {
            return true;
        }
        deptIds.parallelStream().forEach(deptId -> getEntityList(deptId, roleId, list, userDetail));
        return saveBatch(list);
    }

    /**
     * bean转换
     *
     * @param deptId     组织id
     * @param roleId     角色id
     * @param list       组织集
     * @param userDetail 当前用户
     */
    private void getEntityList(Long deptId, Long roleId, List<SystemRoleDeptEntity> list, UserDetail userDetail) {
        SystemRoleDeptEntity e1 = new SystemRoleDeptEntity();
        e1.setRoleId(roleId);
        e1.setDeptId(deptId);
        e1.setVersion(1);
        e1.setCreateTime(new Date());
        e1.setCreateUserId(userDetail.getUserId());
        list.add(e1);
    }
}
