package com.hb0730.cloud.admin.server.permission.feign;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.server.permission.feign.fallback.FeignFactory;
import com.hb0730.cloud.admin.server.permission.system.model.vo.SystemPermissionMenuVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.PERMISSION_MENU_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.PERMISSION_MENU_SERVER;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = PERMISSION_MENU_SERVER, path = PERMISSION_MENU_SERVER_REQUEST, configuration = FeignConfiguration.class, fallbackFactory = FeignFactory.class)
public interface IRemotePermissionMenu {

    /**
     * <p>
     * 新增绑定信息
     * </p>
     *
     * @param vo 菜单权限vo
     * @return 是否成功
     */
    @PostMapping("/save")
    ResultJson save(SystemPermissionMenuVO vo);
}
