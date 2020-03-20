package com.hb0730.cloud.admin.gateway.service;

import com.hb0730.cloud.admin.api.feign.router.model.vo.GatewayRouteDefinition;

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

    /**
     * <p>
     * 保存路由
     * </p>
     *
     * @param definition 路由
     */
    void save(GatewayRouteDefinition definition);

    /**
     * <p>
     * 更新路由
     * </p>
     *
     * @param definition 路由
     */
    void update(GatewayRouteDefinition definition);

    /**
     * <p>
     * 删除id
     * </p>
     *
     * @param id id
     */
    void delete(String id);

    /**
     * <p>
     * 获取info
     * </P>
     *
     * @param id 路由id
     * @return info
     */
    GatewayRouteDefinition getInfo(String id);
}
