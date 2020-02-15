package com.hb0730.cloud.admin.server.oauth2.feign;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.oauth2.model.SystemUserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_SERVER_REQUEST;
import static com.hb0730.cloud.admin.common.util.ServerNameConstants.USER_SERVER;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@FeignClient(value =USER_SERVER, path = USER_SERVER_REQUEST)
public interface IRemoteUser {
    /**
     * <p>
     * 根据用户账号查询
     * </p>
     *
     * @param login 用户账号
     * @return 用户
     */
    @GetMapping("/findUser/{login}")
    ResultJson<SystemUserEntity> findUserByUserName(@PathVariable("login") String login);
}
