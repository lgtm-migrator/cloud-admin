package com.hb0730.cloud.admin.api.feign.user.handler;

import com.hb0730.cloud.admin.api.feign.user.remote.IRemoteUser;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.JsonConvertBeanUtils;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class UserHandler {
    @Autowired
    private IRemoteUser remoteUser;


    /**
     * <p>
     * 根据用户账号获取详情
     * </p>
     *
     * @param userName 用户账号
     * @return 用户详情
     */
    public UserDetail loadUserByUsername(String userName) {
        ResultJson result = remoteUser.findUserByUserName(userName);
        return JsonConvertBeanUtils.convert(result, UserDetail.class);
    }
}
