package com.hb0730.cloud.admin.gateway.controller;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.gateway.model.GatewayRouteDefinition;
import com.hb0730.cloud.admin.gateway.service.ICloudAdminRouteService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
        return ResponseResult.resultSuccess(routers);
    }


    /**
     * 新增路由
     *
     * @param definition 路由
     * @return 是否成功
     */
    @PostMapping("/add")
    public ResultJson save(@RequestBody GatewayRouteDefinition definition) {
        if (Objects.isNull(definition)) {
            return ResponseResult.resultFall("参数为空");
        }
        cloudAdminRouteService.save(definition);
        return ResponseResult.resultSuccess(null);
    }

    /**
     * <p>
     * 更新路由
     * </p>
     *
     * @param routeDefinition 路由
     * @return 是否成功
     */
    @PostMapping("/update")
    public ResultJson update(@RequestBody GatewayRouteDefinition routeDefinition) {
        if (Objects.isNull(routeDefinition)) {
            return ResponseResult.resultFall("参数为空");
        }
        if (StringUtils.isBlank(routeDefinition.getId())) {
            return ResponseResult.resultFall("id为空");
        }
        cloudAdminRouteService.update(routeDefinition);
        return ResponseResult.resultSuccess(null);
    }

    /**
     * <p>
     * 删除路由
     * </p>
     *
     * @param id 路由id
     * @return 是否成功
     */
    @GetMapping("delete/{id}")
    public ResultJson delete(@PathVariable String id) {
        cloudAdminRouteService.delete(id);
        return  ResponseResult.resultSuccess(null);
    }

    @GetMapping("/info/{id}")
    public ResultJson getInfo(@PathVariable String id) {
        GatewayRouteDefinition info = cloudAdminRouteService.getInfo(id);
        return  ResponseResult.resultSuccess( info);
    }
}
