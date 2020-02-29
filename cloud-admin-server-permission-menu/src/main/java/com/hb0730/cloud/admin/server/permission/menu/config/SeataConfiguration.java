package com.hb0730.cloud.admin.server.permission.menu.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Configuration
public class SeataConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource storageDataSource() {
        return new HikariDataSource();
    }
}
