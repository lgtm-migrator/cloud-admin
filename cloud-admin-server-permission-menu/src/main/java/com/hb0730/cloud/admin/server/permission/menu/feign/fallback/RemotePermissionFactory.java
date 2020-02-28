package com.hb0730.cloud.admin.server.permission.menu.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.permission.menu.feign.IRemotePermission;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RemotePermissionFactory implements FallbackFactory<IRemotePermission> {
    @Override
    public IRemotePermission create(Throwable throwable) {
        return new IRemotePermission() {
            @Override
            public ResultJson getPermissionByIds(List<Long> id) {
                return ResponseResult.resultFall("根据ids获取权限失败(熔断)");
            }
        };
    }
}
