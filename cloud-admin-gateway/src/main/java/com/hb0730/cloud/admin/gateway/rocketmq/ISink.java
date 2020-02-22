package com.hb0730.cloud.admin.gateway.rocketmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface ISink {
    /**
     * <p>
     *
     * </p>
     * @return
     */
    @Input("input-router")
    SubscribableChannel inputRouter();
}
