package com.hb0730.cloud.admin.server.permission.system.controller;


import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.permission.system.model.entity.SystemPermissionEntity;
import com.hb0730.cloud.admin.server.permission.system.model.vo.PermissionMenuVO;
import com.hb0730.cloud.admin.server.permission.system.model.vo.SystemPermissionVO;
import com.hb0730.cloud.admin.server.permission.system.service.ISystemPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class SystemPermissionController extends AbstractBaseController<SystemPermissionVO> {
    @Autowired
    private ISystemPermissionService systemPermissionService;

    @Override
    @Deprecated
    public ResultJson save(SystemPermissionVO target) {
        UserDetail currentUser = null;
        try {
            currentUser = getCurrentUser();
        } catch (Exception e) {
            return ResponseResult.resultFall(e.getMessage());
        }
        target.setCreateUserId(currentUser.getId());
        target.setCreateTime(new Date());
        SystemPermissionEntity entity = BeanUtils.transformFrom(target, SystemPermissionEntity.class);
        systemPermissionService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('permission:save')")
    public ResultJson save(@RequestBody PermissionMenuVO permissionMenuVO) {
        UserDetail currentUser = getCurrentUser();
        if (Objects.isNull(currentUser)) {
            return ResponseResult.result(CodeStatusEnum.NON_LOGIN, "获取当前用户失败,请重新登录");
        }
        permissionMenuVO.setVersion(1);
        permissionMenuVO.setCreateTime(new Date());
        permissionMenuVO.setCreateUserId(currentUser.getId());
        systemPermissionService.save(permissionMenuVO);
        return ResponseResult.resultSuccess("保存成功");
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemPermissionVO target) {
        return null;
    }

    @GetMapping("/permission/{id}")
    @Override
    public ResultJson getInfo(@PathVariable Object id) {
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
    @PostMapping("/permission/ids")
    public ResultJson getPermissionByIds(@RequestBody List<Long> id) {
        if (CollectionUtils.isEmpty(id)) {
            return ResponseResult.resultSuccess(null);
        }
        List<SystemPermissionEntity> results = systemPermissionService.listByIds(id);
        List<SystemPermissionVO> vos = BeanUtils.transformFromInBatch(results, SystemPermissionVO.class);
        return ResponseResult.resultSuccess(vos);
    }

    /**
     * <p>
     * 根据id修改权限信息
     * </p>
     *
     * @param vo 权限参数
     * @return 是否成功
     */
    @PostMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('permission:update')")
    public ResultJson updateByIds(@PathVariable Long id, @RequestBody SystemPermissionVO vo) {
        UserDetail currentUser = getCurrentUser();
        if (Objects.isNull(currentUser)) {
            return ResponseResult.result(CodeStatusEnum.NON_LOGIN, "获取当前用户失败");
        }
        vo.setId(id);
        vo.setUpdateTime(new Date());
        vo.setUpdateUserId(currentUser.getId());
        SystemPermissionEntity entity = BeanUtils.transformFrom(vo, SystemPermissionEntity.class);
        systemPermissionService.updateById(entity);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 根据id删除
     * </p>
     *
     * @param id     id
     * @param menuId 菜单id
     * @return 是否成功
     */
    @GetMapping("/unBinding/{id}/{menuId}")
    @PreAuthorize("hasAnyAuthority('permission:remove')")
    public ResultJson deleteByIdAndMenuId(@PathVariable Long id, @PathVariable Long menuId) {
        systemPermissionService.unBinding(id, menuId);
        return ResponseResult.resultSuccess("删除成功");
    }
}

