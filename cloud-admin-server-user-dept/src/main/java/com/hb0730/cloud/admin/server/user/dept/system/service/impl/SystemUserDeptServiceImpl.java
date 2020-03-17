package com.hb0730.cloud.admin.server.user.dept.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.user.dept.system.mapper.SystemUserDeptMapper;
import com.hb0730.cloud.admin.server.user.dept.system.model.entity.SystemUserDeptEntity;
import com.hb0730.cloud.admin.server.user.dept.system.service.ISystemUserDeptService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户组织  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-16
 */
@Service
public class SystemUserDeptServiceImpl extends BaseServiceImpl<SystemUserDeptMapper, SystemUserDeptEntity> implements ISystemUserDeptService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bindingDeptByUserId(@NonNull Long userId, @NonNull List<Long> deptIds, @NonNull UserDetail userDetail) {
        SystemUserDeptEntity entity = new SystemUserDeptEntity();
        entity.setUserId(userId);
        QueryWrapper<SystemUserDeptEntity> queryWrapper = new QueryWrapper<>(entity);
        remove(queryWrapper);
        List<SystemUserDeptEntity> entities = Lists.newArrayList();
        deptIds.parallelStream().forEach(deptId -> {
            SystemUserDeptEntity e1 = new SystemUserDeptEntity();
            e1.setUserId(userId);
            e1.setDeptId(deptId);
            e1.setCreateUserId(userDetail.getUserId());
            e1.setCreateTime(new Date());
            e1.setIsEnabled(1);
            e1.setVersion(1);
            entities.add(e1);
        });
        return saveBatch(entities);
    }
}
