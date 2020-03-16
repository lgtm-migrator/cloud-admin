package com.hb0730.cloud.admin.api.user.dept;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 远程调用用户组织绑定
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IRemoteUserDept {
    /**
     * 获取用户已绑定的组织id
     *
     * @param userId 用户id
     * @return 组织
     */
    ResultJson getDeptByUserId(Long userId);

    /**
     * <p>
     * 根据用户信息绑定组织
     * </p>
     *
     * @param userId  用户id
     * @param deptIds 组织id
     * @return 是否成功
     */
    ResultJson bindingDeptByUserId(Long userId, List<Long> deptIds);
}
