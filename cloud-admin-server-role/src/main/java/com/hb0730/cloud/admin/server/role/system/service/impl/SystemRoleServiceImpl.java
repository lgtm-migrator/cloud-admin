package com.hb0730.cloud.admin.server.role.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.util.RedisJetcacheUtil;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.role.event.RoleEvent;
import com.hb0730.cloud.admin.server.role.handler.RoleHandler;
import com.hb0730.cloud.admin.server.role.system.mapper.SystemRoleMapper;
import com.hb0730.cloud.admin.server.role.system.model.entity.SystemRoleEntity;
import com.hb0730.cloud.admin.server.role.system.model.vo.SystemRoleVO;
import com.hb0730.cloud.admin.server.role.system.service.ISystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统角色  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@Service
public class SystemRoleServiceImpl extends BaseServiceImpl<SystemRoleMapper, SystemRoleEntity> implements ISystemRoleService {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private RoleHandler roleHandler;

    @Override
    public boolean save(SystemRoleEntity entity) {
        boolean b = super.save(entity);
        applicationContext.publishEvent(new RoleEvent(this, applicationContext));
        return b;
    }

    @Override
    public boolean removeById(Serializable id) {
        boolean b = super.removeById(id);
        applicationContext.publishEvent(new RoleEvent(this, applicationContext));
        return b;
    }

    @Override
    public boolean updateById(SystemRoleEntity entity) {
        boolean b = super.updateById(entity);
        applicationContext.publishEvent(new RoleEvent(this, applicationContext));
        return b;
    }

    @Override
    public List<SystemRoleVO> roles() {
        Object value = roleHandler.getValue(RedisJetcacheUtil.RoleCache.KEY_ALL);
        if (Objects.isNull(value)) {
            SystemRoleEntity entity = new SystemRoleEntity();
            entity.setIsEnabled(1);
            QueryWrapper<SystemRoleEntity> queryWrapper = new QueryWrapper<>(entity);
            List<SystemRoleEntity> list = list(queryWrapper);
            roleHandler.updateCache(RedisJetcacheUtil.RoleCache.KEY_ALL, list);
            return BeanUtils.transformFromInBatch(list, SystemRoleVO.class);
        } else {
            return BeanUtils.transformFromInBatch((List) value, SystemRoleVO.class);
        }
    }
}
