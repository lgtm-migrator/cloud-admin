package com.hb0730.cloud.admin.api.feign.role.remote.fallback;

import com.hb0730.cloud.admin.api.feign.role.remote.IRemoteRole;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;

import java.util.List;

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
    public ResultJson getRolesByIds(List<Long> ids) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据id获取角色信息失败,fallback message:" + throwable.getMessage());
    }
}
