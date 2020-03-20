package com.hb0730.cloud.admin.api.feign.user.role.remote.fallback;

import com.hb0730.cloud.admin.api.feign.user.role.remote.IRemoteUserRole;
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
