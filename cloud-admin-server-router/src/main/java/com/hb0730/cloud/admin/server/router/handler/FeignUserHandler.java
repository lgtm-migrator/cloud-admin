package com.hb0730.cloud.admin.server.router.handler;

import com.hb0730.cloud.admin.api.feign.user.remote.IRemoteUser;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.commons.user.model.vo.SystemUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class FeignUserHandler {
    @Autowired
    private IRemoteUser remoteUser;

    /**
     * <p>
     * 根据用户id获取用户名称
     * </p>
     *
     * @param userId 用户id
     * @return 用户名称
     */
    public String findUserNameById(@NonNull Long userId) {
        ResultJson resultJson = remoteUser.findUserById(userId);
        if (CodeStatusEnum.SUCCESS.getCode().equals(resultJson.getStatusCode())) {
            Object data = resultJson.getData();
            if (data instanceof Map) {
                Map map = (Map) data;
                return map.get("name").toString();
            } else {
                SystemUserVO userVO = BeanUtils.transformFrom(data, SystemUserVO.class);
                return userVO == null ? null : userVO.getName();
            }
        } else {
            return null;
        }
    }
}
