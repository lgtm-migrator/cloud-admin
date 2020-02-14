package com.hb0730.cloud.admin.gateway.service.impl;

import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.gateway.feign.IRemoteRouterClient;
import com.hb0730.cloud.admin.gateway.model.GatewayRouteDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 动态路由
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class DynamicRouteServiceImpl implements RouteDefinitionRepository, ApplicationEventPublisherAware {
    private static Logger logger = LoggerFactory.getLogger(DynamicRouteServiceImpl.class);
    private ApplicationEventPublisher publisher;
    private List<RouteDefinition> routeDefinitionList = new ArrayList<>();
    @Autowired
    private IRemoteRouterClient remoteRouterClient;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        logger.debug("获取全部的路由");
        return Flux.fromIterable(routeDefinitionList);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        logger.debug("保存路由");
        return route.flatMap((r) -> {
            GatewayRouteDefinition router = BeanUtils.transformFrom(r, GatewayRouteDefinition.class);
            return Mono.empty();
        });

    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        logger.debug("删除路由");
        return routeId.flatMap(id -> {
            return Mono.defer(() -> Mono.error(
                    new NotFoundException("RouteDefinition not found: " + routeId)));
        });
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostConstruct
    public void init() {
        load();
    }

    private void load() {
        ResultJson routers = remoteRouterClient.getRouters();
        if (CodeStatusEnum.SUCCESS.getCode().equals(routers.getErrCode())) {
            Object data = routers.getData();
            List<GatewayRouteDefinition> gatewayRouteDefinitions = GsonUtils.json2List(data.toString(), GatewayRouteDefinition.class);
            routeDefinitionList = BeanUtils.transformFromInBatch(gatewayRouteDefinitions, RouteDefinition.class);
        }
    }
}
