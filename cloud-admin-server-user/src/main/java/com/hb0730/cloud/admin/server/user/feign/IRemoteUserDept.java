package com.hb0730.cloud.admin.server.user.feign;

import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.server.user.feign.fallback.RemoteUserDeptFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_DEPT_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.USER_DEPT_SERVER;

/**
 * <p>
 * 远程调用用户组织绑定
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = USER_DEPT_SERVER, url = USER_DEPT_SERVER_REQUEST, configuration = FeignConfiguration.class, fallbackFactory = RemoteUserDeptFallbackFactory.class)
public interface IRemoteUserDept extends com.hb0730.cloud.admin.api.user.dept.IRemoteUserDept {
}
