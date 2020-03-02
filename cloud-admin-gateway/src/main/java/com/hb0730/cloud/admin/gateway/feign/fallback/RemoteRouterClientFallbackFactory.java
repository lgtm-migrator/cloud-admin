package com.hb0730.cloud.admin.gateway.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.router.model.vo.GatewayRouteDefinition;
import com.hb0730.cloud.admin.gateway.feign.IRemoteRouterClient;
import feign.hystrix.FallbackFactory;
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
public class RemoteRouterClientFallbackFactory implements FallbackFactory<IRemoteRouterClient> {
    private Logger logger = LoggerFactory.getLogger(RemoteRouterClientFallbackFactory.class);

    @Override
    public IRemoteRouterClient create(Throwable throwable) {
        return new IRemoteRouterClient() {
            @Override
            public ResultJson getRouters() {
                logger.debug("获取全部路由失败(网关熔断)");
                return ResponseResult.resultFall("获取全部路由失败(网关熔断)");
            }

            @Override
            public ResultJson save(GatewayRouteDefinition definition) {
                logger.debug("保存路由失败(网关熔断)");
                return ResponseResult.resultFall("保存网关失败(网关熔断)");
            }

            @Override
            public ResultJson update(GatewayRouteDefinition definition) {
                logger.debug("修改路由{}失败(网关熔断)", definition.getId());
                return ResponseResult.resultFall("修改路由" + definition.getId() + "失败(网关熔断)");
            }

            @Override
            public ResultJson delete(String id) {
                logger.debug("删除路由{}失败(网关熔断)", id);
                return ResponseResult.resultFall("删除路由" + id + "失败(网关熔断)");
            }
        };
    }
}
