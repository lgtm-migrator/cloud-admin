package com.hb0730.cloud.admin.server.user.dept.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.server.user.dept.system.model.entity.SystemUserDeptEntity;
import com.hb0730.cloud.admin.server.user.dept.system.model.vo.SystemUserDeptVO;
import com.hb0730.cloud.admin.server.user.dept.system.service.ISystemUserDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_DEPT_SERVER_REQUEST;

/**
 * <p>
 * 用户组织  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-16
 */
@RestController
@RequestMapping(USER_DEPT_SERVER_REQUEST)
public class SystemUserDeptController extends AbstractBaseController<SystemUserDeptVO> {
    @Autowired
    private ISystemUserDeptService systemUserDeptService;

    @Override
    public ResultJson save(SystemUserDeptVO target) {
        return null;
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemUserDeptVO target) {
        return null;
    }

    @Override
    public ResultJson getInfo(Object id) {
        return null;
    }

    @GetMapping("/getDeptId/{userId}")
    public ResultJson getDeptByUserId(@PathVariable Long userId) {
        SystemUserDeptEntity entity = new SystemUserDeptEntity();
        entity.setIsEnabled(1);
        QueryWrapper<SystemUserDeptEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemUserDeptEntity> list = systemUserDeptService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return ResponseResult.resultSuccess(list);
        }
        List<Long> depts = list.parallelStream().map(SystemUserDeptEntity::getDeptId).collect(Collectors.toList());
        return ResponseResult.resultSuccess(depts);
    }

    /**
     * <p>
     * 根据用户信息绑定组织
     * </p>
     *
     * @param userId  用户id
     * @param deptIds 组织id
     * @return 是否成功
     */
    @PostMapping("/bindingDeptId/{userId}")
    public ResultJson bindingDeptByUserId(@PathVariable Long userId, @RequestBody List<Long> deptIds) {
        systemUserDeptService.bindingDeptByUserId(userId, deptIds, getCurrentUser());
        return ResponseResult.resultSuccess("绑定成功");
    }
}

