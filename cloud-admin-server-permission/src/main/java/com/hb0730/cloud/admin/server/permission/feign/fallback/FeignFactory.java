package com.hb0730.cloud.admin.server.permission.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.permission.feign.IRemotePermissionMenu;
import com.hb0730.cloud.admin.server.permission.system.model.vo.SystemPermissionMenuVO;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class FeignFactory implements FallbackFactory<IRemotePermissionMenu> {
    @Override
    public IRemotePermissionMenu create(Throwable throwable) {
        return new IRemotePermissionMenu() {
            @Override
            public ResultJson save(SystemPermissionMenuVO vo) {
                return ResponseResult.resultFall("保存菜单权限失败（熔断）");
            }

            @Override
            public ResultJson unBinding(Long permissionId, Long menuId) {
                return ResponseResult.resultFall("解除菜单与权限绑定失败(熔断)");
            }
        };
    }
}
