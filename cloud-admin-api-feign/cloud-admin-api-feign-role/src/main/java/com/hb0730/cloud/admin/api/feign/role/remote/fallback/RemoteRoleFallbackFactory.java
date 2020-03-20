package com.hb0730.cloud.admin.api.feign.role.remote.fallback;

import com.hb0730.cloud.admin.api.feign.role.remote.IRemoteRole;
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
public class RemoteRoleFallbackFactory implements FallbackFactory<IRemoteRole> {
    @Override
    public IRemoteRole create(Throwable throwable) {
        return new RemoteRoleFallback(throwable);
    }
}
