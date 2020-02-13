package com.hb0730.cloud.admin.gateway.service.impl;

import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.gateway.model.GatewayRouteDefinition;
import com.hb0730.cloud.admin.gateway.service.ICloudAdminRouteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 自定义路由实现
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class CloudAdminServiceImpl implements ICloudAdminRouteService {
    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    @Override
    public void add(GatewayRouteDefinition routeDefinition) {
        if (Objects.isNull(routeDefinition)) {
            throw new NullPointerException("参数为空");
        }
        RouteDefinition routeDefinition1 = new RouteDefinition();
        BeanUtils.copyProperties(routeDefinition, routeDefinition1);
        dynamicRouteService.save(Mono.just(routeDefinition1)).subscribe();
    }

    @Override
    public List<GatewayRouteDefinition> getRouters() {
        Flux<RouteDefinition> routers = dynamicRouteService.getRouteDefinitions();
        Iterable<RouteDefinition> routeDefinitions = routers.toIterable();
        List<GatewayRouteDefinition> gatewayRouters = Lists.newArrayList();
        for (RouteDefinition routeDefinition : routeDefinitions) {
            GatewayRouteDefinition definition = new GatewayRouteDefinition();
            BeanUtils.copyProperties(routeDefinition, definition);
            gatewayRouters.add(definition);
        }
        return gatewayRouters;
    }

    @Override
    public GatewayRouteDefinition getRouterInfo(String id) {
        if (Objects.isNull(id)) {
            throw new NullPointerException("id 为空");
        }
        return dynamicRouteService.getInfo(id);
    }

    @Override
    public void delete(String id) {
        if (Objects.isNull(id)) {
            throw new NullPointerException("id 为空");
        }
        dynamicRouteService.delete(Mono.just(id)).subscribe();
    }

    @Override
    public void update(GatewayRouteDefinition routeDefinition) {
        if (Objects.isNull(routeDefinition)) {
            throw new NullPointerException("参数为空");
        }
        if (StringUtils.isBlank(routeDefinition.getId())) {
            throw new NullPointerException("路由id为空");
        }
        dynamicRouteService.update(routeDefinition);
    }
}
