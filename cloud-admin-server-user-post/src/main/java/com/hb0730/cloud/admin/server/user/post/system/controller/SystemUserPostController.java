package com.hb0730.cloud.admin.server.user.post.system.controller;


import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.server.user.post.system.model.vo.SystemUserPostVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_POST_SERVER_REQUEST;

/**
 * <p>
 * 用户岗位  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-16
 */
@RestController
@RequestMapping(USER_POST_SERVER_REQUEST)
public class SystemUserPostController extends AbstractBaseController<SystemUserPostVO> {

    @Override
    public ResultJson save(SystemUserPostVO target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemUserPostVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
        return null;
    }
}

