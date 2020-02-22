package com.hb0730.cloud.admin.server.router.system.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.exception.Oauth2Exception;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.router.system.model.entity.SystemRouterEntity;
import com.hb0730.cloud.admin.server.router.system.model.vo.GatewayFilterDefinition;
import com.hb0730.cloud.admin.server.router.system.model.vo.GatewayPredicateDefinition;
import com.hb0730.cloud.admin.server.router.system.model.vo.GatewayRouteDefinition;
import com.hb0730.cloud.admin.server.router.system.service.ISystemRouterService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.ROUTER_SERVER_REQUEST;

/**
 * <p>
 * 系统路由  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-21
 */
@RestController
@RequestMapping(ROUTER_SERVER_REQUEST)
public class SystemRouterController extends AbstractBaseController<GatewayRouteDefinition> {
    private Logger logger = LoggerFactory.getLogger(SystemRouterController.class);

    private ISystemRouterService systemRouterService;

    public SystemRouterController(ISystemRouterService systemRouterService) {
        this.systemRouterService = systemRouterService;
    }

    @PostMapping("/add")
    @Override
    public ResultJson save(@RequestBody GatewayRouteDefinition target) {
        UserDetail currentUser = null;
        try {
            currentUser = getCurrentUser();

        } catch (Oauth2Exception e) {
            return ResponseResult.resultFall(e.getMessage());
        }
        SystemRouterEntity entity = new SystemRouterEntity();
        entity.setIsEnabled(1);
        entity.setIsDelete(0);
        converToEntity(target, entity);
        entity.setCreateTime(new Date());
        entity.setCreateUserId(currentUser.getUserId());
        systemRouterService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
    }

    /**
     * <p>
     * 更新路由
     * </p>
     *
     * @param target 路由
     * @return 是否成功
     */
    @PostMapping("/update")
    public ResultJson update(@RequestBody GatewayRouteDefinition target) throws Exception {
        if (Objects.isNull(target)) {
            return ResponseResult.resultFall("参数为空");
        }
        if (StringUtils.isEmpty(target.getId())) {
            return ResponseResult.resultFall("id为空");
        }
        SystemRouterEntity entity = new SystemRouterEntity();
        converToEntity(target, entity);
        entity.setUpdateTime(new Date());
        entity.setUpdateUserId(Objects.requireNonNull(getCurrentUser()).getUserId());
        systemRouterService.updateById(entity);
        return ResponseResult.resultSuccess("更新成功");
    }

    @GetMapping("/delete/{id}")
    @Override
    public ResultJson delete(@PathVariable Object id) {
        UserDetail currentUser = null;
        try {
            currentUser = getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.result(CodeStatusEnum.NON_LOGIN, "获取当前用户失败,请重新登录");
        }
        SystemRouterEntity entity = new SystemRouterEntity();
        if (!Objects.isNull(currentUser)) {
            entity.setUpdateUserId(currentUser.getUserId());
        }
        entity.setUpdateTime(new Date());
        entity.setId(Long.valueOf(id.toString()));
        UpdateWrapper<SystemRouterEntity> updateWrapper = new UpdateWrapper<>(entity);
        systemRouterService.remove(updateWrapper);
        return ResponseResult.resultSuccess("删除成功");
    }

    @Override
    public ResultJson submit(GatewayRouteDefinition target) {
        return null;
    }

    @Override
    public ResultJson getObject(Object id) {
        return null;
    }

    /**
     * <p>
     * 获取路由集
     * </p>
     *
     * @return 路由
     */
    @GetMapping("/routers")
    public ResultJson routers() {
        List<SystemRouterEntity> list = systemRouterService.list();
        List<GatewayRouteDefinition> definitions = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(entity -> converToBean(entity, definitions));
        }
        return new ResultJson<>(CodeStatusEnum.SUCCESS.getCode(), CodeStatusEnum.SUCCESS.getMessage(), definitions);
    }

    /**
     * <p>
     * 类型转换
     * </p>
     *
     * @param entity      参数类型
     * @param definitions 数据类型
     */
    private void converToBean(SystemRouterEntity entity, List<GatewayRouteDefinition> definitions) {
        GatewayRouteDefinition gatewayRouteDefinition = new GatewayRouteDefinition();
        gatewayRouteDefinition.setId(entity.getId().toString());
        gatewayRouteDefinition.setOrder(entity.getOrder());
        gatewayRouteDefinition.setUri(entity.getUri());
        String filters = entity.getFilters();
        List<GatewayFilterDefinition> gatewayFilterDefinitions = GsonUtils.json2List(filters, GatewayFilterDefinition.class);
        gatewayRouteDefinition.setFilters(gatewayFilterDefinitions);
        String predicates = entity.getPredicates();
        List<GatewayPredicateDefinition> gatewayPredicateDefinitions = GsonUtils.json2List(predicates, GatewayPredicateDefinition.class);
        gatewayRouteDefinition.setPredicates(gatewayPredicateDefinitions);
        definitions.add(gatewayRouteDefinition);
    }

    /**
     * <p>
     * 类型转换
     * </p>
     *
     * @param definition 参数类型
     * @param entity     数据类型
     */
    private void converToEntity(GatewayRouteDefinition definition, SystemRouterEntity entity) {
        if (Objects.isNull(definition)) {
            return;
        }
        if (!Objects.isNull(definition.getId())) {
            entity.setId(Long.parseLong(definition.getId()));
        }
        entity.setUri(definition.getUri());
        entity.setOrder(definition.getOrder());
        List<GatewayFilterDefinition> filters = definition.getFilters();
        String s = JSONArray.toJSONString(filters);
        entity.setFilters(s);
        List<GatewayPredicateDefinition> predicates = definition.getPredicates();
        String s1 = JSONArray.toJSONString(predicates);
        entity.setPredicates(s1);
    }
}

