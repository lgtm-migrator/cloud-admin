package com.hb0730.cloud.admin.common.util;

/**
 * <p>
 * 各模块RequestMapping
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface RequestMappingConstants {

    /**
     * <p>
     * 网关
     * </p>
     */
    String GATEWAY_REQUEST = "/v1/api/gateway/router";


    /**
     * <p>
     * 路由服务
     * </p>
     */
    String ROUTER_SERVER_REQUEST = "/v1/server/system/router";


    /**
     * <p>
     * 用户服务
     * </p>
     */
    String USER_SERVER_REQUEST = "/v1/server/system/user";

    /**
     * <p>
     * 认证服务器
     * </p>
     */
    String OAUTH2_SERVER_REQUEST = "/v1/server/system/oauth2";

    /**
     * <p>
     * 菜单
     * </P>
     */
    String MENU_SERVER_REQUEST = "/v1/server/system/menu";
    /**
     * <p>
     * 权限
     * </p>
     */
    String PERMISSION_SERVER_REQUEST = "/v1/server/system/permission";

    /**
     * <p>
     * 角色
     * </p>
     */
    String ROLE_SERVER_REQUEST = "/v1/server/system/role";

    /**
     * <p>
     * 菜单权限
     * </P>
     */
    String PERMISSION_MENU_SERVER_REQUEST = "/v1/server/system/permissionmenu";

    /**
     * <p>
     * 角色权限
     * </p>
     */
    String ROLE_PERMISSION_SERVER_REQUEST = "/v1/server/system/role/permission";

    /**
     * <p>
     * 用户角色
     * </p>
     */
    String USER_ROLE_SERVER_REQUEST = "/v1/server/system/user/role";
}
