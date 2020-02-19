package com.hb0730.cloud.admin.server.oauth2.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.oauth2.feign.IRemoteUser;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RemoteUserFallbackFactory implements FallbackFactory<IRemoteUser> {
    private static Logger logger = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public IRemoteUser create(Throwable throwable) {
        return new IRemoteUser() {
            @Override
            public ResultJson findUserByUserName(String login) {
                throwable.fillInStackTrace();
                logger.error("根据用户名查询失败（熔断）");
                return ResponseResult.resultFall("根据用户名查询失败（熔断）");
            }
        };
    }
}
