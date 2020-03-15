package com.hb0730.cloud.admin.server.post.dept.system.controller;


import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.server.post.dept.system.model.vo.SystemPostDeptVO;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.POST_DEPT_SERVER_REQUEST;

/**
 * <p>
 * 系统组织岗位  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-15
 */
@RestController
@RequestMapping(POST_DEPT_SERVER_REQUEST)
public class SystemPostDeptController extends AbstractBaseController<SystemPostDeptVO> {

    @Override
    public ResultJson save(SystemPostDeptVO target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemPostDeptVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
        return null;
    }
}

