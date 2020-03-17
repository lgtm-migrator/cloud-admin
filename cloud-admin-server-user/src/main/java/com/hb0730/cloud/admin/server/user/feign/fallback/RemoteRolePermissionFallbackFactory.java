package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.server.user.feign.IRemoteRolePermission;
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
public class RemoteRolePermissionFallbackFactory implements FallbackFactory<IRemoteRolePermission> {
    @Override
    public IRemoteRolePermission create(Throwable throwable) {
        return new RemoteRolePermissionFallback(throwable);
    }
}
