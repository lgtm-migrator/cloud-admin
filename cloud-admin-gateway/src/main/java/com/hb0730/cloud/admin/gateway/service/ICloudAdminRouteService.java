package com.hb0730.cloud.admin.gateway.service;

import com.hb0730.cloud.admin.gateway.model.GatewayRouteDefinition;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface ICloudAdminRouteService {
    /**
     * 新增路由
     *
     * @param routeDefinition 路由
     */
    void add(GatewayRouteDefinition routeDefinition);

    /**
     * <p>
     * 获取全部路由
     * </p>
     *
     * @return 路由
     */
    List<GatewayRouteDefinition> getRouters();

    /**
     * <p>
     * 路由详情
     * </p>
     *
     * @param id 路由id
     * @return 路由
     */
    GatewayRouteDefinition getRouterInfo(String id);

    /**
     * 删除路由
     *
     * @param id 路由id
     */
    void delete(String id);

    /**
     * 更新路由
     *
     * @param routeDefinition 路由
     */
    void update(GatewayRouteDefinition routeDefinition);
}
