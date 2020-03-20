package com.hb0730.cloud.admin.api.feign.router.remote.fallback;

import com.hb0730.cloud.admin.api.feign.router.model.vo.GatewayRouteDefinition;
import com.hb0730.cloud.admin.api.feign.router.remote.IRemoteRouter;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemoteRouterFallback implements IRemoteRouter {

    private Throwable throwable;

    public RemoteRouterFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson getRouters() {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "获取路由失败,fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson save(GatewayRouteDefinition definition) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "保存路由失败,fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson update(GatewayRouteDefinition definition) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "修改路由失败,fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson delete(String id) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "删除路由失败,fallback:" + throwable.getClass().getSimpleName());
    }
}
