package com.hb0730.cloud.admin.gateway.feign;

import com.hb0730.cloud.admin.common.util.ServerNameConstants;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.gateway.feign.fallback.RemoteRouterClientFallback;
import com.hb0730.cloud.admin.gateway.model.GatewayRouteDefinition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 远程路由server
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = ServerNameConstants.ROUTER_SERVER, fallback = RemoteRouterClientFallback.class)
public interface IRemoteRouterClient {

    /**
     * <p>
     * 获取路由
     * </p>
     *
     * @return 路由集
     */
    @GetMapping("/system/router/routers")
    ResultJson getRouters();

    /**
     * <p>
     * 新增路由
     * </p>
     *
     * @param routeDefinition 路由
     */
    @PostMapping("/system/router/add")
    void add(@RequestBody GatewayRouteDefinition routeDefinition);

    /**
     * <p>
     * 删除路由
     * </p>
     *
     * @param id 路由id
     */
    @GetMapping("/system/router/delete/{id}")
    void delete(@PathVariable String id);

    /**
     * <p>
     * 新增路由
     * </p>
     *
     * @param routeDefinition 路由id
     */
    @PostMapping("/system/router/update")
    void update(@RequestBody GatewayRouteDefinition routeDefinition);

    /**
     * <p>
     * 获取详情
     * </p>
     *
     * @param id id
     * @return 详情
     */
    @GetMapping("/system/router/info/{id}")
    ResultJson getInfo(@PathVariable String id);
}
