package com.hb0730.cloud.admin.server.router;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.hb0730.cloud.admin.server.router.rocketmq.ISource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableMethodCache(basePackages = "com.hb0730.cloud.admin.server.router")
@EnableCreateCacheAnnotation
@EnableBinding({ISource.class})
public class RouterServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouterServerApplication.class, args);
    }
}
