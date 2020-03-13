package com.hb0730.cloud.admin.server.router.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.user.model.vo.SystemUserVO;
import com.hb0730.cloud.admin.server.router.feign.IRemoteUser;
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
public class RemoteUserFallbackFactory implements FallbackFactory<IRemoteUser> {
    @Override
    public IRemoteUser create(Throwable throwable) {
        return new IRemoteUser() {
            @Override
            public ResultJson findUserById(Long id) {
                return ResponseResult.resultFall("获取用户信息失败(熔断)");
            }

            @Override
            public ResultJson findUserByUserName(String login) {
                return null;
            }
        };
    }
}
