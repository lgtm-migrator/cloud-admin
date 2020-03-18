package com.hb0730.cloud.admin.server.user.feign.fallback;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.feign.IRemoteDept;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RemoteDeptFallback implements IRemoteDept {
    private Throwable throwable;

    public RemoteDeptFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultJson getDeptByIds(List<Long> ids) {
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "根据id获取组织失败, fallback message:" + throwable.getMessage());
    }
}
