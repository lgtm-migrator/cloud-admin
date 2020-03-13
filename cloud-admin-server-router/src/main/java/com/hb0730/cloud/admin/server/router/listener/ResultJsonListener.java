package com.hb0730.cloud.admin.server.router.listener;

import com.hb0730.cloud.admin.common.util.RedisJetcacheUtil;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.server.router.event.SendResultJsonEvent;
import com.hb0730.cloud.admin.server.router.handler.RouterCacheHandler;
import com.hb0730.cloud.admin.server.router.rocketmq.impl.SenderService;
import com.hb0730.cloud.admin.server.router.system.service.ISystemRouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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
        senderService.send(resultJson);
        ApplicationContext applicationContext = event.getApplicationContext();
        ISystemRouterService service = applicationContext.getBean(ISystemRouterService.class);
        cacheHandler.updateCache(RedisJetcacheUtil.RouterCache.KEY_ALL, service.list());
    }
}
