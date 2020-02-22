package com.hb0730.cloud.admin.gateway.rocketmq.impl;

import com.hb0730.cloud.admin.gateway.service.impl.DynamicRouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Service
public class ConsumerReceive {
    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    @StreamListener("input-router")
    public void receiveInputRouter(String json) {
        System.out.println("消费者》》》》" + json);
        dynamicRouteService.load();
    }

}
