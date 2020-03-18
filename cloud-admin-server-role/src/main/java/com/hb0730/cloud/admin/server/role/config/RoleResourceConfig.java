package com.hb0730.cloud.admin.server.role.config;

import com.hb0730.cloud.admin.server.role.handler.Oauth2AccessDeniedHandler;
import com.hb0730.cloud.admin.server.role.handler.Oauth2ExceptionEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.annotation.Resource;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.ROLE_SERVER_REQUEST;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class RoleResourceConfig extends ResourceServerConfigurerAdapter {
    @Resource
    Oauth2AccessDeniedHandler accessDeniedHandler;
    @Resource
    Oauth2ExceptionEntryPoint exceptionEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(ROLE_SERVER_REQUEST + "/roles").permitAll()
                .antMatchers("/**").hasAnyAuthority("USER");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(exceptionEntryPoint).stateless(false);
        ;
    }
}
