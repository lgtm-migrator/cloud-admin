package com.hb0730.cloud.admin.api.feign.permission.remote;

import com.hb0730.cloud.admin.api.feign.permission.remote.fallback.RemotePermissionFallbackFactory;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.PERMISSION_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.PERMISSION_SERVER;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = PERMISSION_SERVER, path = PERMISSION_SERVER_REQUEST, configuration = FeignConfiguration.class, fallbackFactory = RemotePermissionFallbackFactory.class)
public interface IRemotePermission extends com.hb0730.cloud.admin.api.permission.IRemotePermission {
    /**
     * <p>
     * 根据ids获取权限
     * </p>
     *
     * @param id ids
     * @return 权限级
     */
    @PostMapping("/permission/ids")
    @Override
    ResultJson getPermissionByIds(@RequestBody List<Long> id);
}
