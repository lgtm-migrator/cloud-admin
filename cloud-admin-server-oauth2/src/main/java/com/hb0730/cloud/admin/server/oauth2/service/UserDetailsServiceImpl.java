package com.hb0730.cloud.admin.server.oauth2.service;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.server.oauth2.feign.IRemoteUser;
import com.hb0730.cloud.admin.server.oauth2.model.SystemUserEntity;
import com.hb0730.cloud.admin.server.oauth2.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
@Service("userDetailServer")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IRemoteUser remoteUser;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ResultJson<SystemUserEntity> result = remoteUser.findUserByUserName(userName);
        if (CodeStatusEnum.SUCCESS.getCode().equals(result.getErrCode())) {
            SystemUserEntity data = result.getData();
            if (!Objects.isNull(data)) {
                //密码校验
                UserDetail userDetail = new UserDetail();
                userDetail.setUserId(data.getId().intValue());
                userDetail.setUsername(data.getLogin());
                userDetail.setPassword(data.getLoginPasswd());
                userDetail.setEmail(data.getLoginEmail());
                userDetail.setStatus(1);
                userDetail.setSalt(data.getSalt());
                userDetail.setPerms("USER");
                return userDetail;
            }
        }
        return null;
    }
}
