package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.feign.IRemoteUserDept;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemoteUserDeptFallback implements IRemoteUserDept {
    private Throwable throwable;

    public RemoteUserDeptFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson getDeptByUserId(Long userId) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "获取组织失败,fallback:" + throwable.getClass().getSimpleName());
    }

    @Override
    public ResultJson bindingDeptByUserId(Long userId, List<Long> deptIds) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "绑定组织失败,fallback:" + throwable.getClass().getSimpleName());
    }
}
