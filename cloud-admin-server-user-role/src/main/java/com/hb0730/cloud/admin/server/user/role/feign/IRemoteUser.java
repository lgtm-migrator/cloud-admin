package com.hb0730.cloud.admin.server.user.role.feign;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.server.user.role.feign.fallback.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.USER_SERVER;

/**
 * <p>
 * 查询用户
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = USER_SERVER, path = USER_SERVER_REQUEST, configuration = {FeignConfiguration.class}, fallbackFactory = RemoteUserFallbackFactory.class)
public interface IRemoteUser {
    /**
     * <p>
     * 根据用户id查询用户
     * </p>
     *
     * @param userId 用户id
     * @return 用户
     */
    @GetMapping("/{userId}")
    ResultJson findUserById(@PathVariable("userId") long userId);
}
