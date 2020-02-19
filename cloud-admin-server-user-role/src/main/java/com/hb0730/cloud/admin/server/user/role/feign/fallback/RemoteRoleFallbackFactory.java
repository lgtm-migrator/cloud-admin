package com.hb0730.cloud.admin.server.user.role.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.role.feign.IRemoteRole;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RemoteRoleFallbackFactory implements FallbackFactory<IRemoteRole> {
    private Logger logger = LoggerFactory.getLogger(RemoteRoleFallbackFactory.class);

    @Override
    public IRemoteRole create(Throwable throwable) {
        return new IRemoteRole() {
            @Override
            public ResultJson findRoleById(long roleId) {
                logger.error("cloud-admin-server-user-role:根据角色{}查询角色失败(feign熔断)", roleId);
                return ResponseResult.resultFall("根据角色" + roleId + "查询角色失败(feign熔断)");
            }

            @Override
            public ResultJson findRoleByUserId(long userId) {
                logger.error("cloud-admin-server-user-role:根据用户{}查询角色失败(feign熔断)", userId);
                return ResponseResult.resultFall("根据用户" + userId + "查询角色失败(feign熔断)");
            }
        };
    }
}
