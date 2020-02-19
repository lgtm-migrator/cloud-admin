package com.hb0730.cloud.admin.server.role.permission;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
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
@EnableMethodCache(basePackages = "com.hb0730.cloud.admin.server.role.permission")
@EnableCreateCacheAnnotation
public class RolePermissionServerApplication {
}
