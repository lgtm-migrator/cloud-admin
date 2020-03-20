package com.hb0730.cloud.admin.api.feign.user.remote.fallback;

import com.hb0730.cloud.admin.api.feign.user.remote.IRemoteUser;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<IRemoteUser> {
    @Override
    public IRemoteUser create(Throwable throwable) {
        return new RemoteUserFallback(throwable);
    }
}
