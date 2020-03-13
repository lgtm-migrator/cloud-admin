package com.hb0730.cloud.admin.gateway.feign.fallback;

import com.hb0730.cloud.admin.gateway.feign.IRemoteRouterClient;
import org.springframework.stereotype.Component;


/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RemoteRouterClientFallbackFactory implements feign.hystrix.FallbackFactory<IRemoteRouterClient> {
    @Override
    public IRemoteRouterClient create(Throwable throwable) {
        return new RemoteRouterFallback(throwable);
    }
}
