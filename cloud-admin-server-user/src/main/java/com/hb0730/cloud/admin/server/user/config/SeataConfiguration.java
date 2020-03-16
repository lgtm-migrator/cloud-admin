package com.hb0730.cloud.admin.server.user.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
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
    private Logger log = LoggerFactory.getLogger(SeataConfiguration.class);

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource storageDataSource() {
        log.info("Building hikariDataSource......");
        return DataSourceBuilder.create().type(HikariDataSource.class).build();

    }
}
