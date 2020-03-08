package com.hb0730.cloud.admin.server.dept.system.controller;


import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.vo.BaseDomainVO;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.DEPT_SERVER_REQUEST;

/**
 * <p>
 * 系统部门  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-08
 */
@RestController
@RequestMapping(DEPT_SERVER_REQUEST)
public class SystemDeptController extends AbstractBaseController {

    @Override
    public ResultJson save(BaseDomainVO target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(BaseDomainVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
        return null;
    }
}

