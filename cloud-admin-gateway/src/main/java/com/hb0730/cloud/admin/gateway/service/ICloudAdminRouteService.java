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
     * <p>
     * 获取全部路由
     * </p>
     *
     * @return 路由
     */
    List<GatewayRouteDefinition> getRouters();
}
