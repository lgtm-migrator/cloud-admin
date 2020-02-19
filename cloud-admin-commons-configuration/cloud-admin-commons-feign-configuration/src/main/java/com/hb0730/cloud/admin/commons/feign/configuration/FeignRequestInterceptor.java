package com.hb0730.cloud.admin.commons.feign.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) return;
        HttpServletRequest request = attributes.getRequest();
        String accessToken = request.getParameter("access_token");
        if (!StringUtils.isBlank(accessToken)) {
            requestTemplate.header("authorization", "Bearer " + accessToken);
        } else {
            String authorization = request.getHeader("authorization");
            if (!StringUtils.isBlank(authorization)) {
                requestTemplate.header("authorization", authorization);
            }
        }
    }
}
