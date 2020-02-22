package com.hb0730.cloud.admin.server.router.listener;

import com.hb0730.cloud.admin.server.router.event.SendMessageEvent;
import com.hb0730.cloud.admin.server.router.rocketmq.impl.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class SendListener implements ApplicationListener<SendMessageEvent> {
    @Autowired
    private SenderService senderService;

    @Override
    public void onApplicationEvent(SendMessageEvent sendEvent) {
        senderService.send(sendEvent.getMessage());
    }
}
