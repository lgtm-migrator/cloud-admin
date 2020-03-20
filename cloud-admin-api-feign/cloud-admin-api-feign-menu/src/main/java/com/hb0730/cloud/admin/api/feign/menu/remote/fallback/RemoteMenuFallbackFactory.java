package com.hb0730.cloud.admin.api.feign.menu.remote.fallback;

import com.hb0730.cloud.admin.api.feign.menu.remote.IRemoteMenu;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 熔断
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RemoteMenuFallbackFactory implements FallbackFactory<IRemoteMenu> {
    @Override
    public IRemoteMenu create(Throwable throwable) {
        return new RemoteMenuFallback(throwable);
    }
}
