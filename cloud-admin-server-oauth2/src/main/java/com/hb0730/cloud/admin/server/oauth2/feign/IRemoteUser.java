package com.hb0730.cloud.admin.server.oauth2.feign;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.server.oauth2.feign.fallback.RemoteUserFallbackFactory;
import com.hb0730.cloud.admin.server.oauth2.model.SystemUserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
     * 根据用户账号查询
     * </p>
     *
     * @param login 用户账号
     * @return 用户
     */
    @RequestMapping(value = "findUser/{login}", method = RequestMethod.GET)
    ResultJson<SystemUserEntity> findUserByUserName(@PathVariable("login") String login);
}
