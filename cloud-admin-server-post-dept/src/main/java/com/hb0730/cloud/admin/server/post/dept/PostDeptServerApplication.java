package com.hb0730.cloud.admin.server.post.dept;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableMethodCache(basePackages = "com.hb0730.cloud.admin.server.post.dept")
@EnableCreateCacheAnnotation
public class PostDeptServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostDeptServerApplication.class, args);
    }
}
