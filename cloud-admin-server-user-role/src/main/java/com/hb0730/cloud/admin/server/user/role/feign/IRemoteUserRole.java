package com.hb0730.cloud.admin.server.user.role.feign;

import org.springframework.cloud.openfeign.FeignClient;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_ROLE_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.USER_ROLE_SERVER;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = USER_ROLE_SERVER, path = USER_ROLE_SERVER_REQUEST)
public interface IRemoteUserRole {

}
