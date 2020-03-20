package com.hb0730.cloud.admin.api.feign.dept.remote.fallback;

import com.hb0730.cloud.admin.api.feign.dept.remote.IRemoteUserDept;
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
        return new RemoteUserDeptFallback(throwable);
    }
}
