package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.server.user.feign.IRemoteUserPost;
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
public class RemoteUserPostFallbackFactory implements FallbackFactory<IRemoteUserPost> {
    @Override
    public IRemoteUserPost create(Throwable throwable) {
        return new RemoteUserPostFallback(throwable);
    }
}
