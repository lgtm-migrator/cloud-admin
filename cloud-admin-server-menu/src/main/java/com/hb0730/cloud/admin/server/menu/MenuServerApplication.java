package com.hb0730.cloud.admin.server.menu;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableMethodCache(basePackages = "com.hb0730.cloud.admin.server.menu")
@EnableCreateCacheAnnotation
@EnableFeignClients(basePackages = "com.hb0730.cloud.admin.api.feign")
@ComponentScan(basePackages = "com.hb0730.cloud.admin")
public class MenuServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenuServerApplication.class, args);
    }
}
