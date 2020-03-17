package com.hb0730.cloud.admin.api.user.dept;

import com.hb0730.cloud.admin.common.web.response.ResultJson;

import java.util.List;

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

    /**
     * <p>
     *根据用户id删除
     * </p>
     *
     * @param userId 用户id
     * @return 是否成功
     */
    ResultJson removeByUserId(Long userId);
}
