package com.hb0730.cloud.admin.server.role.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.cloud.admin.common.util.RedisJetcacheUtil;
import com.hb0730.cloud.admin.server.role.event.RoleEvent;
import com.hb0730.cloud.admin.server.role.handler.RoleHandler;
import com.hb0730.cloud.admin.server.role.system.model.entity.SystemRoleEntity;
import com.hb0730.cloud.admin.server.role.system.service.ISystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RoleListener {
    @Autowired
    private ISystemRoleService systemPostService;
    @Autowired
    private RoleHandler handler;

    @EventListener
    @Async
    public void onApplicationEvent(RoleEvent event) {
        SystemRoleEntity entity = new SystemRoleEntity();
        entity.setIsEnabled(1);
        QueryWrapper<SystemRoleEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemRoleEntity> list = systemPostService.list(queryWrapper);
        handler.updateCache(RedisJetcacheUtil.RoleCache.KEY_ALL, list);
    }
}
