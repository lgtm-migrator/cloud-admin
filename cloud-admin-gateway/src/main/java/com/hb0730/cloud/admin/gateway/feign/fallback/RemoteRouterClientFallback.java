package com.hb0730.cloud.admin.gateway.feign.fallback;

import com.hb0730.cloud.admin.common.exception.GatewayException;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.gateway.feign.IRemoteRouterClient;
import com.hb0730.cloud.admin.gateway.model.GatewayRouteDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RemoteRouterClientFallback implements IRemoteRouterClient {
    private Logger logger = LoggerFactory.getLogger(RemoteRouterClientFallback.class);

    @Override
    public ResultJson getRouters() {
        logger.debug("获取全部路由异常");
        throw new GatewayException("获取路由失败(网关熔断降级保护)");
    }

    @Override
    public void add(GatewayRouteDefinition routeDefinition) {
        logger.debug("新增路由异常");
        throw new GatewayException("添加路由失败(网关熔断降级保护)");
    }

    @Override
    public void delete(String id) {
        logger.debug("删除路由异常");
        throw new GatewayException("删除路由失败(网关熔断降级保护)");
    }

    @Override
    public void update(GatewayRouteDefinition routeDefinition) {
        logger.debug("更新路由异常");
        throw new GatewayException("更新路由失败(网关熔断降级保护)");
    }

    @Override
    public ResultJson getInfo(String id) {
        logger.debug("获取路由{}详情异常",id);
        throw new GatewayException("更新路由"+id+"失败(网关熔断降级保护)");
    }
}
