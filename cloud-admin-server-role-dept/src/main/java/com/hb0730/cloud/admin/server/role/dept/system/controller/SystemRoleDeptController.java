package com.hb0730.cloud.admin.server.role.dept.system.controller;


import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.role.dept.system.model.vo.SystemRoleDeptVO;
import com.hb0730.cloud.admin.server.role.dept.system.service.ISystemRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private ISystemRoleDeptService systemRoleDeptService;

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

    /**
     * 根据角色id获取组织范围
     *
     * @param roleId 角色id
     * @return 组织
     */
    @GetMapping("/getDeptIdByRoleId/{roleId}")
    public ResultJson getDeptIdByRoleId(@PathVariable Long roleId) {
        List<Long> result = systemRoleDeptService.getDeptIdByRoleId(roleId);
        return ResponseResult.resultSuccess(result);
    }

    /**
     * <p>
     * 绑定组织id
     * </p>
     *
     * @param roleId  角色id
     * @param deptIds 组织id
     * @return 是否成功
     */
    @PostMapping("/bindingDept/{roleId}")
    public ResultJson bindingDeptIds(@PathVariable Long roleId, @RequestBody List<Long> deptIds) {
        if (CollectionUtils.isEmpty(deptIds)) {
            return ResponseResult.resultFall("组织id为空");
        }
        systemRoleDeptService.bindingDeptIds(roleId, deptIds, getCurrentUser());
        return ResponseResult.resultSuccess("绑定成功");
    }
}

