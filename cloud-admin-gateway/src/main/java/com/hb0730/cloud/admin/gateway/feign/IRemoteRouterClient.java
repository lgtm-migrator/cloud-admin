package com.hb0730.cloud.admin.gateway.feign;

import com.hb0730.cloud.admin.api.router.IRemoteRouter;
import com.hb0730.cloud.admin.common.util.ServerNameConstants;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.commons.router.model.vo.GatewayRouteDefinition;
import com.hb0730.cloud.admin.gateway.feign.fallback.RemoteRouterClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.ROUTER_SERVER_REQUEST;

/**
 * <p>
 * 远程路由server
 * //, configuration = FeignConfiguration.class, fallbackFactory = RemoteRouterClientFactory.class
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = ServerNameConstants.ROUTER_SERVER, path = ROUTER_SERVER_REQUEST, configuration = {FeignConfiguration.class}, fallbackFactory = RemoteRouterClientFallbackFactory.class)
public interface IRemoteRouterClient extends IRemoteRouter {
    /**
     * <p>
     * 获取路由
     * </p>
     *
     * @return 路由集
     */
    @GetMapping("/routers")
    @Override
    ResultJson getRouters();

    /**
     * <p>
     * 保存路由
     * </p>
     *
     * @param definition 路由
     * @return 是否成功
     */
    @PostMapping("/add")
    @Override
    ResultJson save(@RequestBody GatewayRouteDefinition definition);

    /**
     * <p>
     * 更新路由
     * </p>
     *
     * @param definition 路由
     * @return 是否成功
     */
    @PostMapping("/update")
    @Override
    ResultJson update(@RequestBody GatewayRouteDefinition definition);

    /**
     * <p>
     * 删除路由
     * </p>
     *
     * @param id 路由id
     * @return 是否成功
     */
    @GetMapping("/delete/{id}")
    @Override
    ResultJson delete(@PathVariable("id") String id);

}
