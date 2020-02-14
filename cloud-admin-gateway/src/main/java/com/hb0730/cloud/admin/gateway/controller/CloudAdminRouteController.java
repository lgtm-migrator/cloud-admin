package com.hb0730.cloud.admin.gateway.controller;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.gateway.model.GatewayRouteDefinition;
import com.hb0730.cloud.admin.gateway.service.ICloudAdminRouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class CloudAdminRouteController {

    private ICloudAdminRouteService cloudAdminRouteService;

    public CloudAdminRouteController(ICloudAdminRouteService cloudAdminRouteService) {
        this.cloudAdminRouteService = cloudAdminRouteService;
    }

    /**
     * 获取路由
     *
     * @return 路由
     */
    @GetMapping("routers")
    public ResultJson getRouter() {
        List<GatewayRouteDefinition> routers = cloudAdminRouteService.getRouters();
        return new ResultJson<>(CodeStatusEnum.SUCCESS.getCode(), CodeStatusEnum.SUCCESS.getMessage(), routers);
    }
}
