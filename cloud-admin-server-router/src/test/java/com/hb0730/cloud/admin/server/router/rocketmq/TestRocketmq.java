package com.hb0730.cloud.admin.server.router.rocketmq;

import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.router.event.SendResultJsonEvent;
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
        applicationContext.publishEvent(new SendResultJsonEvent(this, ResponseResult.resultSuccess("测试"), applicationContext));
    }
}
