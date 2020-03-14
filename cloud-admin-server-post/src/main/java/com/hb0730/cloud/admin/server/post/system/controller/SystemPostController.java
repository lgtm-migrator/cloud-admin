package com.hb0730.cloud.admin.server.post.system.controller;


import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.server.post.system.model.entity.vo.SystemPostVO;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.POST_SERVER_REQUEST;

/**
 * <p>
 * 系统岗位  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-14
 */
@RestController
@RequestMapping(POST_SERVER_REQUEST)
public class SystemPostController extends AbstractBaseController<SystemPostVO> {

    @Override
    public ResultJson save(SystemPostVO target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemPostVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
        return null;
    }
}

