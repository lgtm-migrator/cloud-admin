package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.server.user.feign.IRemoteUserDept;
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
public class RemoteUserDeptFallbackFactory implements FallbackFactory<IRemoteUserDept> {
    @Override
    public IRemoteUserDept create(Throwable throwable) {
        return null;
    }
}
