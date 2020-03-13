package com.hb0730.cloud.admin.common.web.utils;

import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

/**
 * <p>
 * security 工具类 用户获取当前用户
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
            return GsonUtils.json2Bean(GsonUtils.json2String(principal), UserDetail.class);
        }
        return null;
    }
}
