package com.hb0730.cloud.admin.server.oauth2.service;

import com.alibaba.fastjson.JSONArray;
import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.JsonConvertBeanUtils;
import com.hb0730.cloud.admin.commons.user.model.vo.SystemUserVO;
import com.hb0730.cloud.admin.server.oauth2.feign.IRemoteUser;
import com.hb0730.cloud.admin.server.oauth2.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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
        ResultJson result = remoteUser.findUserByUserName(userName);
        return JsonConvertBeanUtils.convert(result, UserDetail.class);
    }
}
