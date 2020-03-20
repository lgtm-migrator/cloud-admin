package com.hb0730.cloud.admin.api.feign.router.handler;

import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.api.feign.router.remote.IRemoteRouter;
import com.hb0730.cloud.admin.common.web.exception.BusinessException;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.JsonConvertBeanUtils;
import com.hb0730.cloud.admin.commons.router.model.vo.GatewayRouteDefinition;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RemoteRouterHandler {
    @Autowired
    private IRemoteRouter remoteRouter;

    /**
     * <p>
     * 获取可用的全部路由
     * </p>
     *
     * @return 路由信息
     * @since {@link GatewayRouteDefinition}
     */
    public List<GatewayRouteDefinition> getRouters() {
        ResultJson routers = remoteRouter.getRouters();
        if (!CodeStatusEnum.SUCCESS.getCode().equals(routers.getStatusCode())) {
            throw new BusinessException("获取路由集失败, message:" + routers.getData().toString());
        }
        if (Objects.isNull(routers.getData())) {
            return Lists.newArrayList();
        }
        return JsonConvertBeanUtils.convertList(routers.getData(), GatewayRouteDefinition.class);
    }

    /**
     * <p>
     * 更新路由信息
     * </p>
     *
     * @param routeDefinition 路由信息
     */
    public void update(GatewayRouteDefinition routeDefinition) {
        if (Objects.isNull(routeDefinition)) {
            return;
        }
        ResultJson result = remoteRouter.update(routeDefinition);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getStatusCode())) {
            throw new BusinessException("更新路由信息失败, message:" + result.getData().toString());
        }
    }

    /**
     * 根据路由id删除
     *
     * @param id 路由id
     */
    public void delete(String id) {
        if (Strings.isBlank(id)) {
            return;
        }
        ResultJson result = remoteRouter.delete(id);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getStatusCode())) {
            throw new BusinessException("删除路由失败, message:" + result.getData().toString());
        }
    }

    /**
     * <p>
     * 保存路由
     * </p>
     *
     * @param router 路由信息
     */
    public void save(GatewayRouteDefinition router) {
        if (Objects.isNull(router)) {
            return;
        }
        ResultJson result = remoteRouter.save(router);
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getStatusCode())) {
            throw new BusinessException("保存路由失败, message:" + result.getData().toString());
        }
    }
}
