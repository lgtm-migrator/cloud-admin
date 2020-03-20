package com.hb0730.cloud.admin.api.feign.dept.remote.fallback;

import com.hb0730.cloud.admin.api.feign.dept.remote.IRemoteUserDept;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.user.dept.model.vo.UserDeptParamsVO;

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
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "获取组织失败,fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson bindingDeptByUserId(Long userId, List<Long> deptIds) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "绑定组织失败,fallback message:" + throwable.getMessage());
    }

    @Override
    public ResultJson removeByUserId(Long userId) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "删除组织失败,fallback message" + throwable.getMessage());
    }

    @Override
    public ResultJson getPage(Integer page, Integer pageSize, UserDeptParamsVO params) {
        throwable.printStackTrace();
        return ResponseResult.result(CodeStatusEnum.FALL_BACK, "获取用户组织失败,fallback Message:" + throwable.getMessage());
    }
}
