package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.server.user.feign.IRemotePermissionMenu;
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
public class RemotePermissionMenuFallbackFactory implements FallbackFactory<IRemotePermissionMenu> {
    @Override
    public IRemotePermissionMenu create(Throwable throwable) {
        return new RemotePermissionMenuFallback(throwable);
    }
}
