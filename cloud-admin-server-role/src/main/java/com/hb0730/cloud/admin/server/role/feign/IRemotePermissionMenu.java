package com.hb0730.cloud.admin.server.role.feign;

import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.PERMISSION_MENU_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.PERMISSION_MENU_SERVER;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = PERMISSION_MENU_SERVER, path = PERMISSION_MENU_SERVER_REQUEST, configuration = FeignConfiguration.class)
public interface IRemotePermissionMenu {
}
