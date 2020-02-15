package com.hb0730.cloud.admin.server.oauth2.service;

import com.hb0730.cloud.admin.common.exception.Oauth2Exception;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.server.oauth2.feign.IRemoteUser;
import com.hb0730.cloud.admin.server.oauth2.model.SystemUserEntity;
import com.hb0730.cloud.admin.server.oauth2.model.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private IRemoteUser remoteUser;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ResultJson<SystemUserEntity> result = remoteUser.findUserByUserName(userName);
        if (CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            SystemUserEntity data = result.getData();
            if (!Objects.isNull(data)) {
                UserDetail userDetail = new UserDetail();
                userDetail.setUserId(data.getId().intValue());
                userDetail.setUsername(data.getLogin());
                userDetail.setPassword(data.getLoginPasswd());
                userDetail.setEmail(data.getLoginEmail());
                return userDetail;
            }
        }
        throw new Oauth2Exception("未找到用户");
    }
}
