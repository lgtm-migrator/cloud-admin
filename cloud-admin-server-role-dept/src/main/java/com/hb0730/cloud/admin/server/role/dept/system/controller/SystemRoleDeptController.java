package com.hb0730.cloud.admin.server.role.dept.system.controller;


import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.server.role.dept.system.model.vo.SystemRoleDeptVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.ROLE_DEPT_SERVER_REQUEST;

/**
 * <p>
 * 系统角色组织  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-16
 */
@RestController
@RequestMapping(ROLE_DEPT_SERVER_REQUEST)
public class SystemRoleDeptController extends AbstractBaseController<SystemRoleDeptVO> {

    @Override
    public ResultJson save(SystemRoleDeptVO target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemRoleDeptVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
        return null;
    }
}

