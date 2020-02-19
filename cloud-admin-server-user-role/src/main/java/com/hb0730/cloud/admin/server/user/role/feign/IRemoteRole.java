package com.hb0730.cloud.admin.server.user.role.feign;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.server.user.role.feign.fallback.RemoteRoleFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
public interface IRemoteRole {

    /**
     * <p>
     * 根据角色id查询用户
     * </p>
     *
     * @param roleId 角色id
     * @return 角色
     */
    @GetMapping("/findRole/role/{roleId}")
    ResultJson findRoleById(@PathVariable("roleId") long roleId);

    /**
     * <p>
     * 获取用户所属角色
     * </p>
     *
     * @param userId 用户id
     * @return 角色
     */
    @GetMapping("/findRole/user/{userId}")
    ResultJson findRoleByUserId(@PathVariable("userId") long userId);
}
