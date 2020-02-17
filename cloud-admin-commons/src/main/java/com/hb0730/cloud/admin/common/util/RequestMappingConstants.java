package com.hb0730.cloud.admin.common.util;

/**
 * <p>
 * 各模块RequestMapping
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RequestMappingConstants {

    private RequestMappingConstants() {

    }

    /**
     * <p>
     * 网关
     * </p>
     */
    public static final String GATEWAY_REQUEST = "/v1/api/gateway/router";


    /**
     * <p>
     * 路由服务
     * </p>
     */
    public static final String ROUTER_SERVER_REQUEST = "/v1/server/system/router";


    /**
     * <p>
     * 用户服务
     * </p>
     */
    public static final String USER_SERVER_REQUEST = "/v1/server/system/user";

    /**
     * <p>
     * 认证服务器
     * </p>
     */
    public static final String OAUTH2_SERVER_REQUEST = "/v1/server/server/oauth2";

}
