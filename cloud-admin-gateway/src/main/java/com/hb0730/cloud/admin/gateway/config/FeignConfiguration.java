package com.hb0730.cloud.admin.gateway.config;

import feign.Feign;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * feign配置
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Configuration
public class FeignConfiguration {
    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
