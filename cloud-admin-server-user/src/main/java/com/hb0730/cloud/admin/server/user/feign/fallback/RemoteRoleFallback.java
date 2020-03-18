package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.feign.IRemoteRole;
import org.checkerframework.checker.units.qual.C;

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
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据id获取角色信息失败,fallback message:" + throwable.getMessage());
    }
}
