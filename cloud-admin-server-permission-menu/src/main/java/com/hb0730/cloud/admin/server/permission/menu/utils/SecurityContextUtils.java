package com.hb0730.cloud.admin.server.permission.menu.utils;

import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class SecurityContextUtils {
    /**
     * <p>
     * 获取spring security context
     * </p>
     *
     * @return {@link SecurityContextHolder}
     */
    public static SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

    /**
     * <p>
     * 获取认证信息
     * </p>
     *
     * @return {@link Authentication}
     */
    public static Authentication getAuthentication() {
        return getContext().getAuthentication();
    }

    /**
     * <p>
     * 获取当前认证用户
     * </p>
     *
     * @return {@link UserDetail}
     */
    public static UserDetail getCurrentUser() throws Exception {
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof Map) {
            Map map = (Map) principal;
            return BeanUtils.map2bean(map, UserDetail.class);
        }
        return null;
    }
}
