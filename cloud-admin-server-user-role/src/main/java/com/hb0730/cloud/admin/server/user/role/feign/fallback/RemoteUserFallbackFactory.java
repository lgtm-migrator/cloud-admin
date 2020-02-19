package com.hb0730.cloud.admin.server.user.role.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.role.feign.IRemoteUser;
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
public class RemoteUserFallbackFactory implements FallbackFactory<IRemoteUser> {
    private Logger logger = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public IRemoteUser create(Throwable throwable) {
        return new IRemoteUser() {
            @Override
            public ResultJson findUserById(long userId) {
                logger.error("用户角色查询失败(feign熔断)");
                return ResponseResult.resultFall("用户角色查询失败(feign熔断)");
            }
        };
    }
}
