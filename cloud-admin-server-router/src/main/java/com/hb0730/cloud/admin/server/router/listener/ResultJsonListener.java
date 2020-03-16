package com.hb0730.cloud.admin.server.router.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.cloud.admin.common.util.RedisJetcacheUtil;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.server.router.event.SendResultJsonEvent;
import com.hb0730.cloud.admin.server.router.handler.RouterCacheHandler;
import com.hb0730.cloud.admin.server.router.rocketmq.impl.SenderService;
import com.hb0730.cloud.admin.server.router.system.model.entity.SystemRouterEntity;
import com.hb0730.cloud.admin.server.router.system.service.ISystemRouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class ResultJsonListener {
    @Autowired
    private SenderService senderService;
    @Autowired
    private RouterCacheHandler cacheHandler;

    @EventListener
    @Async
    public void onApplicationEvent(SendResultJsonEvent event) {
        ResultJson resultJson = event.getResultJson();
        ApplicationContext applicationContext = event.getApplicationContext();
        ISystemRouterService service = applicationContext.getBean(ISystemRouterService.class);
        SystemRouterEntity entity = new SystemRouterEntity();
        entity.setIsEnabled(1);
        QueryWrapper<SystemRouterEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemRouterEntity> entityList = service.list(queryWrapper);
        cacheHandler.updateCache(RedisJetcacheUtil.RouterCache.KEY_ALL, entityList);
        senderService.send(resultJson);
    }
}
