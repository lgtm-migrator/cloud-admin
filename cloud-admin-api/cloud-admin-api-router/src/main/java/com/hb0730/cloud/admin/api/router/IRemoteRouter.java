package com.hb0730.cloud.admin.api.router;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.router.model.vo.GatewayRouteDefinition;

/**
 * <p>
 * 远程调用 路由服务 接口
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IRemoteRouter {
    /**
     * <p>
     * 获取路由
     * </p>
     *
     * @return 路由集
     */
    ResultJson getRouters();

    /**
     * <p>
     * 保存路由
     * </p>
     *
     * @param definition 路由
     * @return 是否成功
     */
    ResultJson save(GatewayRouteDefinition definition);

    /**
     * <p>
     * 更新路由
     * </p>
     *
     * @param definition 路由
     * @return 是否成功
     */
    ResultJson update(GatewayRouteDefinition definition);

    /**
     * <p>
     * 删除路由
     * </p>
     *
     * @param id 路由id
     * @return 是否成功
     */
    ResultJson delete(String id);

}
