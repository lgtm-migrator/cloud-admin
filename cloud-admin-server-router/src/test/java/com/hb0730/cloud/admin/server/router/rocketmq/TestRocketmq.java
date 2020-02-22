package com.hb0730.cloud.admin.server.router.rocketmq;

import com.hb0730.cloud.admin.server.router.event.SendMessageEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRocketmq {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        applicationContext.publishEvent(new SendMessageEvent(this, "测试"));
    }
}
