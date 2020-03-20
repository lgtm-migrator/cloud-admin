package com.hb0730.cloud.admin.api.feign.router.remote.fallback;

import com.hb0730.cloud.admin.api.feign.router.remote.IRemoteRouter;
import org.springframework.stereotype.Component;


/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RemoteRouterClientFallbackFactory implements feign.hystrix.FallbackFactory<IRemoteRouter> {
    @Override
    public IRemoteRouter create(Throwable throwable) {
        return new RemoteRouterFallback(throwable);
    }
}
