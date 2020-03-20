package com.hb0730.cloud.admin.server.user.role.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.role.feign.IRemoteRole;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemoteRoleFallback implements IRemoteRole {
    private Throwable throwable;

    public RemoteRoleFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson findRoleById(long roleId) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据角色查询角色失败(feign熔断) fallback message" + throwable.getMessage());
    }

    @Override
    public ResultJson findRoleByUserId(long userId) {
        throwable.printStackTrace();
        return ResponseResult.resultFall("根据用户查询角色失败(feign熔断) fallback message" + throwable.getMessage());
    }
}
