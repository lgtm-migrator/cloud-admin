package com.hb0730.cloud.admin.gateway.controller;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.gateway.model.GatewayRouteDefinition;
import com.hb0730.cloud.admin.gateway.service.ICloudAdminRouteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * CloudAdmin 路由
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class CloudAdminRouteController {
    @Autowired
    ICloudAdminRouteService service;

    /**
     * 获取路由
     *
     * @return 路由
     */
    @GetMapping("routers")
    public ResultJson getRouter() {
        List<GatewayRouteDefinition> routers = service.getRouters();
        return new ResultJson<>(CodeStatusEnum.SUCCESS.getCode(), CodeStatusEnum.SUCCESS.getMessage(), routers);
    }

    /**
     * 新增路由
     *
     * @param gatewayRouteDefinition GatewayRouteDefinition
     * @return 是否成功
     */
    @PostMapping("/add")
    public ResultJson add(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        service.add(gatewayRouteDefinition);
        return new ResultJson<>(CodeStatusEnum.SUCCESS.getCode(), CodeStatusEnum.SUCCESS.getMessage(), "新增成功");
    }

    /**
     * <p>
     * 获取路由详情
     * </p>
     *
     * @param id 路由id
     * @return 详情
     */
    @GetMapping("/router/{id}")
    public ResultJson getRouterInfo(@PathVariable String id) {
        GatewayRouteDefinition routerInfo = service.getRouterInfo(id);
        return new ResultJson<>(CodeStatusEnum.SUCCESS.getCode(), CodeStatusEnum.SUCCESS.getMessage(), routerInfo);
    }

    /**
     * <p>
     * 删除路由
     * </p>
     *
     * @param id 路由id
     * @return 是否成功
     */
    @GetMapping("/deleteRouter/{id}")
    public ResultJson delete(@PathVariable String id) {
        service.delete(id);
        return new ResultJson<>(CodeStatusEnum.SUCCESS.getCode(), CodeStatusEnum.SUCCESS.getMessage(), "删除成功");
    }

    @PostMapping("/updateRouter")
    public ResultJson update(@RequestBody GatewayRouteDefinition routeDefinition) {
        service.update(routeDefinition);
        return new ResultJson<>(CodeStatusEnum.SUCCESS.getCode(), CodeStatusEnum.SUCCESS.getMessage(), "更新成功");
    }
}
