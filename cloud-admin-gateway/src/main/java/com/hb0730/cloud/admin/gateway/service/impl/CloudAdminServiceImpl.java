package com.hb0730.cloud.admin.gateway.service.impl;

import com.hb0730.cloud.admin.common.exception.NullPointerException;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.commons.router.model.vo.GatewayRouteDefinition;
import com.hb0730.cloud.admin.gateway.service.ICloudAdminRouteService;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.URI;
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
@NoArgsConstructor
public class CloudAdminServiceImpl implements ICloudAdminRouteService {
    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;


    @Override
    public List<GatewayRouteDefinition> getRouters() {
        return dynamicRouteService.getRouters();
    }

    @Override
    public void save(GatewayRouteDefinition definition) {
        if (Objects.isNull(definition)) {
            throw new NullPointerException("参数为空");
        }
        RouteDefinition routeDefinition = convertToBean(definition);
        assert routeDefinition != null;
        dynamicRouteService.save(Mono.just(routeDefinition)).subscribe();
    }

    @Override
    public void update(GatewayRouteDefinition definition) {
        if (Objects.isNull(definition)) {
            throw new NullPointerException("参数为空");
        }
        if (StringUtils.isBlank(definition.getId())) {
            throw new NullPointerException("id为空");
        }
        dynamicRouteService.update(definition);
    }

    @Override
    public void delete(String id) {
        dynamicRouteService.delete(Mono.just(id)).subscribe();
    }

    @Override
    public GatewayRouteDefinition getInfo(String id) {
        if (StringUtils.isBlank(id)) {
            throw new NullPointerException("参数id为空");
        }
        return dynamicRouteService.getInfo(id);
    }

    /**
     * <p>
     * 类型转换
     * </p>
     *
     * @param routeDefinition 路由
     * @return router
     */
    private RouteDefinition convertToBean(GatewayRouteDefinition routeDefinition) {
        if (Objects.isNull(routeDefinition)) {
            return null;
        }
        RouteDefinition routeDefinition1 = BeanUtils.transformFrom(routeDefinition, RouteDefinition.class);
        assert routeDefinition1 != null;
        routeDefinition1.setUri(URI.create(routeDefinition.getUri()));
        return routeDefinition1;
    }
}
