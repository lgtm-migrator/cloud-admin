package com.hb0730.cloud.admin.server.user.role.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.role.feign.IRemoteUser;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemoteUserFallback implements IRemoteUser {
    private Throwable throwable;

    public RemoteUserFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson findUserById(long userId) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "用户角色查询失败(feign熔断) fallback message" + throwable.getMessage());
    }
}
