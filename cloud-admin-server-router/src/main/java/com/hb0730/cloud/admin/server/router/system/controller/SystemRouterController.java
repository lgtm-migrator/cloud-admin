package com.hb0730.cloud.admin.server.router.system.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.exception.Oauth2Exception;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.util.PageInfoUtil;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.commons.user.model.vo.SystemUserVO;
import com.hb0730.cloud.admin.server.router.feign.IRemoteUser;
import com.hb0730.cloud.admin.server.router.system.model.entity.SystemRouterEntity;
import com.hb0730.cloud.admin.server.router.system.model.vo.*;
import com.hb0730.cloud.admin.server.router.system.service.ISystemRouterService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
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
public class SystemRouterController extends AbstractBaseController<SystemRouterVO> {
    private Logger logger = LoggerFactory.getLogger(SystemRouterController.class);

    private ISystemRouterService systemRouterService;

    @Autowired
    private IRemoteUser remoteUser;

    public SystemRouterController(ISystemRouterService systemRouterService) {
        this.systemRouterService = systemRouterService;
    }

    @PostMapping("/add")
    @Override
    public ResultJson save(@RequestBody SystemRouterVO target) {
        UserDetail currentUser = null;
        try {
            currentUser = getCurrentUser();

        } catch (Oauth2Exception e) {
            return ResponseResult.result(CodeStatusEnum.NON_LOGIN, e.getMessage());
        }
        SystemRouterEntity entity = BeanUtils.transformFrom(target, SystemRouterEntity.class);
        assert entity != null;
        entity.setCreateTime(new Date());
        entity.setCreateUserId(currentUser.getId());
        entity.setVersion(1);
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
    public ResultJson update(@RequestBody SystemRouterVO target) throws Exception {
        if (Objects.isNull(target)) {
            return ResponseResult.resultFall("参数为空");
        }
        if (Objects.isNull(target.getId())) {
            return ResponseResult.resultFall("路由id为空");
        }
        SystemRouterEntity targetEntity = BeanUtils.transformFrom(target, SystemRouterEntity.class);
        SystemRouterEntity entity = systemRouterService.getById(target.getId());
        assert targetEntity != null;
        targetEntity.setUpdateTime(new Date());
        targetEntity.setUpdateUserId(Objects.requireNonNull(getCurrentUser()).getId());
        BeanUtils.updateProperties(targetEntity, entity);
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
            entity.setUpdateUserId(currentUser.getId());
        }
        entity.setUpdateTime(new Date());
        entity.setId(Long.valueOf(id.toString()));
        systemRouterService.updateById(entity);
        systemRouterService.removeById(entity.getId());
        return ResponseResult.resultSuccess("删除成功");
    }

    @Override
    public ResultJson submit(SystemRouterVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
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
        List<SystemRouterEntity> list = systemRouterService.getListByCache();
        List<GatewayRouteDefinition> definitions = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(entity -> converToBean(entity, definitions));
        }
        return ResponseResult.resultSuccess(definitions);
    }

    /**
     * <p>
     * 分页的路由集
     * </p>
     *
     * @param page     页数
     * @param pageSize 每页数量
     * @return 分页的路由集
     */
    @PostMapping("/routers/{page}/{pageSize}")
    @PreAuthorize("hasAnyAuthority('router:query')")
    public ResultJson routers(@PathVariable int page, @PathVariable int pageSize, @RequestBody RouterParamsVO paramsVO) {
        PageHelper.startPage(page, pageSize);
        SystemRouterEntity entity = BeanUtils.transformFrom(paramsVO, SystemRouterEntity.class);
        QueryWrapper<SystemRouterEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemRouterEntity> list = systemRouterService.list(queryWrapper);
        PageInfo<SystemRouterEntity> pageInfo = new PageInfo<>(list);
        PageInfo<SystemRouterVO> info = PageInfoUtil.toBean(pageInfo, SystemRouterVO.class);
        info.getList().forEach(vo -> {
            String userName = getUserName(vo.getCreateUserId());
            vo.setCreateUserName(userName);
            userName = getUserName(vo.getUpdateUserId());
            vo.setUpdateUserName(userName);
        });
        return ResponseResult.resultSuccess(info);
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
        gatewayRouteDefinition.setCreateTime(entity.getCreateTime());
        gatewayRouteDefinition.setCreateUserId(entity.getCreateUserId());
        gatewayRouteDefinition.setDescription(entity.getDescription());
        gatewayRouteDefinition.setUpdateTime(entity.getUpdateTime());
        gatewayRouteDefinition.setUpdateUserId(entity.getUpdateUserId());
        gatewayRouteDefinition.setVersion(entity.getVersion());
        String filters = entity.getFilters();
        if (!StringUtils.isBlank(filters)) {
            List<GatewayFilterDefinition> gatewayFilterDefinitions = GsonUtils.json2List(filters, GatewayFilterDefinition.class);
            gatewayRouteDefinition.setFilters(gatewayFilterDefinitions);
        }
        String predicates = entity.getPredicates();
        if (!StringUtils.isBlank(predicates)) {
            List<GatewayPredicateDefinition> gatewayPredicateDefinitions = GsonUtils.json2List(predicates, GatewayPredicateDefinition.class);
            gatewayRouteDefinition.setPredicates(gatewayPredicateDefinitions);
        }
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
        entity.setDescription(definition.getDescription());
        List<GatewayFilterDefinition> filters = definition.getFilters();
        String s = JSONArray.toJSONString(filters);
        entity.setFilters(s);
        List<GatewayPredicateDefinition> predicates = definition.getPredicates();
        String s1 = JSONArray.toJSONString(predicates);
        entity.setPredicates(s1);
    }

    /**
     * <p>
     * 获取用户名称
     * </p>
     *
     * @param userId 用户id
     * @return 用户名称
     */
    private String getUserName(Long userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        ResultJson resultJson = remoteUser.findUserById(userId);
        if (CodeStatusEnum.SUCCESS.getCode().equals(resultJson.getErrCode())) {
            Object data = resultJson.getData();
            if (data instanceof Map) {
                Map map = (Map) data;
                return map.get("name").toString();
            } else {
                SystemUserVO userVO = BeanUtils.transformFrom(data, SystemUserVO.class);
                return userVO == null ? null : userVO.getName();
            }
        } else {
            return null;
        }
    }
}

