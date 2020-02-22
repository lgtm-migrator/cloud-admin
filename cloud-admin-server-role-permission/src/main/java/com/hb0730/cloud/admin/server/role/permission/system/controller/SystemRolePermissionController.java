package com.hb0730.cloud.admin.server.role.permission.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.role.permission.system.model.entity.SystemRolePermissionEntity;
import com.hb0730.cloud.admin.server.role.permission.system.service.ISystemRolePermissionService;
import com.hb0730.cloud.admin.server.role.permission.system.vo.SystemRolePermissionVO;
import com.hb0730.cloud.admin.server.role.permission.utils.SecurityContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.ROLE_PERMISSION_SERVER_REQUEST;

/**
 * <p>
 * 角色权限  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@RestController
@RequestMapping(ROLE_PERMISSION_SERVER_REQUEST)
public class SystemRolePermissionController extends AbstractBaseController<SystemRolePermissionVO> {
    @Autowired
    private ISystemRolePermissionService systemRolePermissionService;

    @PostMapping("/save")
    @Override
    public ResultJson save(@RequestBody SystemRolePermissionVO target) {
        UserDetail currentUser = null;
        try {
            currentUser = SecurityContextUtils.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.resultFall("获取当前用户失败,请重新登录");
        }
        if (Objects.isNull(currentUser)) {
            return ResponseResult.resultFall("获取当前用户失败,请重新登录");
        }
        target.setCreateUserId(currentUser.getUserId());
        target.setCreateTime(new Date());
        SystemRolePermissionEntity entity = BeanUtils.transformFrom(target, SystemRolePermissionEntity.class);
        systemRolePermissionService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemRolePermissionVO target) {
        return null;
    }

    @GetMapping("/role/permission/{id}")
    @Override
    public ResultJson getObject(@PathVariable Object id) {
        SystemRolePermissionEntity result = systemRolePermissionService.getById(id.toString());
        return ResponseResult.resultSuccess(result);
    }

    /**
     * <p>
     * 根据id获取结果集
     * </p>
     *
     * @param ids id
     * @return 结果集
     */
    @PostMapping("/role/permission")
    public ResultJson getRolePermissionByIds(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResponseResult.resultSuccess(null);
        }
        List<SystemRolePermissionEntity> results = systemRolePermissionService.listByIds(ids);
        return ResponseResult.resultSuccess(results);
    }

    /**
     * <p>
     * 获取全部
     * </P>
     *
     * @return 结果集
     */
    @GetMapping("/role/permission")
    public ResultJson getRolePermission() {
        List<SystemRolePermissionEntity> result = systemRolePermissionService.list();
        return ResponseResult.resultSuccess(result);
    }

    /**
     * <p>
     * 根据角色id获取
     * </p>
     *
     * @param roleId 角色id
     * @return 结果集
     */
    @GetMapping("/role/permission/role/{roleId}")
    public ResultJson getRolePermissionByRoleId(@PathVariable Long roleId) {
        SystemRolePermissionEntity entity = new SystemRolePermissionEntity();
        entity.setRoleId(roleId);
        QueryWrapper<SystemRolePermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(entity);
        List<SystemRolePermissionEntity> results = systemRolePermissionService.list(queryWrapper);
        return ResponseResult.resultSuccess(results);
    }

    /**
     * <p>
     * 根据权限获取
     * </p>
     *
     * @param permissionId 权限id
     * @return 结果集
     */
    @GetMapping("/role/permission/permission/{permissionId}")
    public ResultJson getRolePermissionByPermissionId(@PathVariable Long permissionId) {
        SystemRolePermissionEntity entity = new SystemRolePermissionEntity();
        entity.setPermissionId(permissionId);
        QueryWrapper<SystemRolePermissionEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemRolePermissionEntity> results = systemRolePermissionService.list(queryWrapper);
        return ResponseResult.resultSuccess(results);
    }
}

