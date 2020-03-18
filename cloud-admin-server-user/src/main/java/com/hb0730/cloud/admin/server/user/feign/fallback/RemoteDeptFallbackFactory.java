package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.server.user.feign.IRemoteDept;
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
public class RemoteDeptFallbackFactory implements FallbackFactory<IRemoteDept> {
    @Override
    public IRemoteDept create(Throwable throwable) {
        return new RemoteDeptFallback(throwable);
    }
}
