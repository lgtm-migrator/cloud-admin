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
    public static final String GATEWAY_REQUEST = "/route";
    /**
     * <p>
     * 路由服务
     * </p>
     */
    public static final String ROUTER_SERVER_REQUEST = "/admin/system/router";


    /**
     * <p>
     * 用户服务
     * </p>
     */
    public static final String USER_SERVER_REQUEST = "/admin/system/user";


}
