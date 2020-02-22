package com.hb0730.cloud.admin.server.router.listener;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.server.router.event.SendResultJsonEvent;
import com.hb0730.cloud.admin.server.router.rocketmq.impl.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @EventListener
    @Async
    public void onApplicationEvent(SendResultJsonEvent event) {
        ResultJson resultJson = event.getResultJson();
        senderService.send(resultJson);
    }
}
