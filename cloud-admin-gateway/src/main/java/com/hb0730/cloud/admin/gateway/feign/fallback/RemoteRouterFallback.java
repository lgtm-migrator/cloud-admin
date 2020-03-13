package com.hb0730.cloud.admin.gateway.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.router.model.vo.GatewayRouteDefinition;
import com.hb0730.cloud.admin.gateway.feign.IRemoteRouterClient;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemoteRouterFallback implements IRemoteRouterClient {

    private Throwable throwable;

    public RemoteRouterFallback(Throwable throwable) {
        this.throwable = throwable;
    }
    @Override
    public ResultJson getRouters() {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "获取路由失败,fallback:"+throwable.getClass().getSimpleName());
    }

    @Override
    public ResultJson save(GatewayRouteDefinition definition) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "保存路由失败,fallback:"+throwable.getClass().getSimpleName());
    }

    @Override
    public ResultJson update(GatewayRouteDefinition definition) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "修改路由失败,fallback:"+throwable.getClass().getSimpleName());
    }

    @Override
    public ResultJson delete(String id) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "删除路由失败,fallback:"+throwable.getClass().getSimpleName());
    }
}
