package com.hb0730.cloud.admin.server.oauth2.feign;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.commons.user.model.vo.SystemUserVO;
import com.hb0730.cloud.admin.server.oauth2.feign.fallback.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
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
public interface IRemoteUser extends com.hb0730.cloud.admin.api.user.IRemoteUser {
    /**
     * <p>
     * 获取用户信息
     * </p>
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    @Override
    ResultJson findUserById(@PathVariable("id") Long id);
    /**
     * <p>
     * 根据用户账号查询
     * </p>
     *
     * @param login 用户账号
     * @return 用户
     */
    @RequestMapping(value = "findUser/{login}", method = RequestMethod.GET)
    @Override
    ResultJson findUserByUserName(@PathVariable("login") String login);

    /**
     * 根据用户id获取权限
     * @param userId 用户id
     * @return 权限
     */
    @GetMapping("/permission/{userId}")
    ResultJson getPermissionByUserId(@PathVariable("userId") Long userId);
}
