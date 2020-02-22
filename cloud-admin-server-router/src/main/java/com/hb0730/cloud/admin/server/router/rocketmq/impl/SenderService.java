package com.hb0730.cloud.admin.server.router.rocketmq.impl;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.server.router.rocketmq.ISource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Service
public class SenderService {
    @Autowired
    private ISource source;

    /**
     * <p>
     * 发送消息
     * </p>
     *
     * @param msg 消息
     */
    public void send(String msg) {
        source.outputRouter().send(MessageBuilder.withPayload(msg).build());
    }

    /**
     * <p>
     * 发送对象
     * </P>
     *
     * @param json ResultJson
     */
    public void send(ResultJson json) {
        source.outputRouter().send(MessageBuilder.withPayload(json).build());
    }
}
