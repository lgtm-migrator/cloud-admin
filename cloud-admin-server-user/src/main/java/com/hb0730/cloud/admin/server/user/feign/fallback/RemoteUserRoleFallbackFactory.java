package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.server.user.feign.IRemoteUserRole;
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
public class RemoteUserRoleFallbackFactory implements FallbackFactory<IRemoteUserRole> {
    @Override
    public IRemoteUserRole create(Throwable throwable) {
        return new RemoteUserRoleFallback(throwable);
    }
}
