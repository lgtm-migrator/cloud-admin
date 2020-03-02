package com.hb0730.cloud.admin.api.user;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.commons.user.model.vo.SystemUserVO;

/**
 * <p>
 * 远程调用用户服务接口
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IRemoteUser {
    /**
     * <p>
     * 获取用户信息
     * </p>
     *
     * @param id 用户id
     * @return 用户信息
     */
    ResultJson findUserById(Long id);

    /**
     * <p>
     *     根据用户账号查询
     * </p>
     * @param login 用户账号
     * @return 用户
     */
    ResultJson<SystemUserVO> findUserByUserName(String login);
}
