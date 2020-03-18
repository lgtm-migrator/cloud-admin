package com.hb0730.cloud.admin.server.permission.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.permission.feign.IRemotePermissionMenu;
import com.hb0730.cloud.admin.server.permission.system.model.vo.SystemPermissionMenuVO;
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
        return new RemotePermissionFallback(throwable);
    }
}
