package com.hb0730.cloud.admin.server.permission.system.controller;


import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.permission.system.model.entity.SystemPermissionEntity;
import com.hb0730.cloud.admin.server.permission.system.service.ISystemPermissionService;
import com.hb0730.cloud.admin.server.permission.utils.SecurityContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.PERMISSION_SERVER_REQUEST;

/**
 * <p>
 * 系统权限  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@RestController
@RequestMapping(PERMISSION_SERVER_REQUEST)
public class SystemPermissionController extends AbstractBaseController<SystemPermissionEntity> {
    @Autowired
    private ISystemPermissionService systemPermissionService;

    @PostMapping("/save")
    @Override
    public ResultJson save(@RequestBody SystemPermissionEntity target) {
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
        systemPermissionService.save(target);
        return ResponseResult.resultSuccess("保存成功");
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemPermissionEntity target) {
        return null;
    }

    @GetMapping("/permission/{id}")
    @Override
    public ResultJson gitObject(@PathVariable Object id) {
        SystemPermissionEntity result = systemPermissionService.getById(id.toString());
        return ResponseResult.resultSuccess(result);
    }

    /**
     * <p>
     * 获取全部权限
     * </P>
     *
     * @return 权限
     */
    @GetMapping("/permission/all")
    public ResultJson getPermission() {
        List<SystemPermissionEntity> result = systemPermissionService.list();
        return ResponseResult.resultSuccess(result);
    }

    /**
     * <p>
     * 根据id集获取权限
     * </P>
     *
     * @param id id集合
     * @return 权限
     */
    @PostMapping("/permission/id")
    public ResultJson getPermissionByIds(@RequestBody List<Long> id) {
        if (CollectionUtils.isEmpty(id)) {
            return ResponseResult.resultSuccess(null);
        }
        List<SystemPermissionEntity> results = systemPermissionService.listByIds(id);
        return ResponseResult.resultSuccess(results);
    }

}

