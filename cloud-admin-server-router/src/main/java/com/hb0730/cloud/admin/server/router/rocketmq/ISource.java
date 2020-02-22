package com.hb0730.cloud.admin.server.router.rocketmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * <p>
 * 生产者
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface ISource {
    /**
     * <p>
     * 路由
     * </p>
     *
     * @return
     */
    @Output("output-router")
    MessageChannel outputRouter();
}
