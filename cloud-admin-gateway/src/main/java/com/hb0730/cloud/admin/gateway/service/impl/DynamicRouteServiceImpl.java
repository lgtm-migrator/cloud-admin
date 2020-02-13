package com.hb0730.cloud.admin.gateway.service.impl;

import com.hb0730.cloud.admin.common.exception.GatewayException;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.gateway.feign.IRemoteRouterClient;
import com.hb0730.cloud.admin.gateway.model.GatewayRouteDefinition;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
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
@AllArgsConstructor
@NoArgsConstructor
public class DynamicRouteServiceImpl implements RouteDefinitionRepository {
    private static Logger logger = LoggerFactory.getLogger(DynamicRouteServiceImpl.class);
    /**
     * 路由远程调用
     */
    @Resource
    IRemoteRouterClient remoteRouterClient;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        logger.debug("获取全部的路由");
        ResultJson routers = remoteRouterClient.getRouters();
        if (!CodeStatusEnum.SUCCESS.getCode().equals(routers.getErrCode())) {
            throw new GatewayException("获取路由失败");
        }
        Object data = routers.getData();

        List<GatewayRouteDefinition> routeDefinitions = BeanUtils.transformFromInBatch((List) data, GatewayRouteDefinition.class);
        List<RouteDefinition> router = BeanUtils.transformFromInBatch(routeDefinitions, RouteDefinition.class);
        return Flux.fromIterable(router);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        logger.debug("保存路由");
        return route.flatMap((r) -> {
            GatewayRouteDefinition router = BeanUtils.transformFrom(r, GatewayRouteDefinition.class);
            remoteRouterClient.add(router);
            return Mono.empty();
        });

    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        logger.debug("删除路由");
        return routeId.flatMap(id -> {
            remoteRouterClient.delete(id);
            return Mono.defer(() -> Mono.error(
                    new NotFoundException("RouteDefinition not found: " + routeId)));
        });
    }

    /**
     * <p>
     * 获取路由info
     * </p>
     *
     * @param id info
     * @return info
     */
    public GatewayRouteDefinition getInfo(String id) {
        logger.debug("获取路由info");
        ResultJson result = remoteRouterClient.getInfo(id);
        if (CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            return (GatewayRouteDefinition) result.getData();
        }
        throw new GatewayException("获取路由" + id + "失败");
    }

    /**
     * 更新路由
     *
     * @param routeDefinition 路由
     */
    public void update(GatewayRouteDefinition routeDefinition) {
        logger.debug("更新路由");
        remoteRouterClient.update(routeDefinition);
    }
}
