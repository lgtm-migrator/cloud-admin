package com.hb0730.cloud.admin.server.permission.menu.feign;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.feign.configuration.FeignConfiguration;
import com.hb0730.cloud.admin.server.permission.menu.feign.fallback.RemoteMenuFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.MENU_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.MENU_SERVER;

/**
 * <p>
 * feign调用
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(name = MENU_SERVER, path = MENU_SERVER_REQUEST, configuration = FeignConfiguration.class, fallbackFactory = RemoteMenuFactory.class)
public interface IRemoteMenu {

    /**
     * <p>
     * 根据id获取菜单
     * </p>
     *
     * @param id id
     * @return 菜单
     */
    @GetMapping("/{id}")
    ResultJson getMenuById(@PathVariable("id") Long id);
}
