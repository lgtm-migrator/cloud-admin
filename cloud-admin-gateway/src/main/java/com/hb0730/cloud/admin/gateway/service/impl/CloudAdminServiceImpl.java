package com.hb0730.cloud.admin.gateway.service.impl;

import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.gateway.model.GatewayRouteDefinition;
import com.hb0730.cloud.admin.gateway.service.ICloudAdminRouteService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 自定义路由实现
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Service
@NoArgsConstructor
public class CloudAdminServiceImpl implements ICloudAdminRouteService {
    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;


    @Override
    public List<GatewayRouteDefinition> getRouters() {
        Flux<RouteDefinition> routeDefinitions = dynamicRouteService.getRouteDefinitions();
        Iterator<RouteDefinition> iterator = routeDefinitions.toIterable().iterator();
        List<GatewayRouteDefinition> gatewayRouteDefinitions = Lists.newArrayList();
        while (iterator.hasNext()) {
            RouteDefinition next = iterator.next();
            GatewayRouteDefinition gatewayRouteDefinition = BeanUtils.transformFrom(next, GatewayRouteDefinition.class);
            gatewayRouteDefinitions.add(gatewayRouteDefinition);
        }
        return gatewayRouteDefinitions;
    }
}
