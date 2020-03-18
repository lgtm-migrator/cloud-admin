package com.hb0730.cloud.admin.server.user.feign;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.server.user.feign.fallback.RemoteRoleFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.ROLE_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.ROLE_SERVER;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = ROLE_SERVER, path = ROLE_SERVER_REQUEST, configuration = FeignConfiguration.class, fallbackFactory = RemoteRoleFallbackFactory.class)
public interface IRemoteRole extends com.hb0730.cloud.admin.api.role.IRemoteRole {

    /**
     * <p>
     * 根据角色id获取角色信息
     * </p>
     *
     * @param ids 角色id
     * @return 角色信息
     */
    @PostMapping("/roles")
    @Override
    ResultJson getRolesByIds(@RequestBody List<Long> ids);
}
