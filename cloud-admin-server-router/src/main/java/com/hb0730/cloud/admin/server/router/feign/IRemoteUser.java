package com.hb0730.cloud.admin.server.router.feign;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.server.router.feign.fallback.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.USER_SERVER;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(value = USER_SERVER, path = USER_SERVER_REQUEST, configuration = FeignConfiguration.class, fallbackFactory = RemoteUserFallbackFactory.class)
public interface IRemoteUser {

    /**
     * <p>
     * 获取用户信息
     * </p>
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    ResultJson findUserById(@PathVariable("id") Long id);
}
