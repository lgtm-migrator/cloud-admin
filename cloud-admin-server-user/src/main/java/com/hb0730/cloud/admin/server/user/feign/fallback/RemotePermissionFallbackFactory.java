package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.server.user.feign.IRemotePermission;
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
public class RemotePermissionFallbackFactory implements FallbackFactory<IRemotePermission> {
    @Override
    public IRemotePermission create(Throwable throwable) {
        return new RemotePermissionFallback(throwable);
    }
}
