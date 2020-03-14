package com.hb0730.cloud.admin.server.dept.system.controller;


import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.dept.system.model.vo.SystemDeptVO;
import com.hb0730.cloud.admin.server.dept.system.service.ISystemDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class SystemDeptController extends AbstractBaseController<SystemDeptVO> {
    @Autowired
    private ISystemDeptService systemDeptService;

    @Override
    public ResultJson save(SystemDeptVO target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemDeptVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
        return null;
    }

    /**
     * <p>
     * 获取树形
     * </p>
     *
     * @return 树形组织
     */
    @GetMapping("/tree")
    public ResultJson getTree() {
        return ResponseResult.resultSuccess(systemDeptService.getTree());
    }
}

