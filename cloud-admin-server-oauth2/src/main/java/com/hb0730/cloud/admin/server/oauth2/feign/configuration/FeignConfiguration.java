//package com.hb0730.cloud.admin.server.oauth2.feign.configuration;
//
//import com.hb0730.cloud.admin.commons.feign.configuration.FeignRequestInterceptor;
//import feign.Feign;
//import feign.Logger;
//import feign.RequestInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * <p>
// * feign配置
// * </P>
// *
// * @author bing_huang
// * @since V1.0
// */
//@Configuration
//public class FeignConfiguration {
//
//    @Bean
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
//
//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
//
//
//    @Bean
//    public RequestInterceptor oauth2FeignRequestInterceptor() {
//        return new FeignRequestInterceptor();
//    }
//}
