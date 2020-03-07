package com.hb0730.cloud.admin.server.permission.menu.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.permission.menu.feign.IRemoteMenu;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 熔断
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RemoteMenuFactory implements FallbackFactory<IRemoteMenu> {
    @Override
    public IRemoteMenu create(Throwable throwable) {
        return new IRemoteMenu() {
            @Override
            public ResultJson getMenuById(Long id) {
                return ResponseResult.resultFall("根据id获取菜单失败（熔断）");
            }

            @Override
            public ResultJson getMenusByParentId(Long parentId) {
                return ResponseResult.resultFall("根据父id获取菜单失败（熔断）");
            }

        };
    }
}
