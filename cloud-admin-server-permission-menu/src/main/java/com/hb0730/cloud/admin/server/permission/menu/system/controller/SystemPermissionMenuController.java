package com.hb0730.cloud.admin.server.permission.menu.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hb0730.cloud.admin.common.exception.Oauth2Exception;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.permission.menu.system.model.entity.SystemPermissionMenuEntity;
import com.hb0730.cloud.admin.server.permission.menu.system.model.vo.PermissionMenuListVO;
import com.hb0730.cloud.admin.server.permission.menu.system.model.vo.PermissionMenuVO;
import com.hb0730.cloud.admin.server.permission.menu.system.model.vo.SystemPermissionMenuVO;
import com.hb0730.cloud.admin.server.permission.menu.system.service.ISystemPermissionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.PERMISSION_MENU_SERVER_REQUEST;

/**
 * <p>
 * 菜单权限  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@RestController
@RequestMapping(PERMISSION_MENU_SERVER_REQUEST)
public class SystemPermissionMenuController extends AbstractBaseController<SystemPermissionMenuVO> {
    @Autowired
    private ISystemPermissionMenuService systemPermissionMenuService;

    @PostMapping("/save")
    @Override
    @PreAuthorize("hasAnyAuthority('permission:save','menu:save')")
    public ResultJson save(@RequestBody SystemPermissionMenuVO target) {
        UserDetail currentUser = null;
        try {
            currentUser = getCurrentUser();
        } catch (Oauth2Exception e) {
            return ResponseResult.resultFall("获取当前用户失败,请重新登录");
        }
        target.setCreateUserId(currentUser.getId());
        target.setCreateTime(new Date());
        SystemPermissionMenuEntity entity = BeanUtils.transformFrom(target, SystemPermissionMenuEntity.class);
        systemPermissionMenuService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemPermissionMenuVO target) {
        return null;
    }

    @GetMapping("/permission/menu/{id}")
    @Override
    @PreAuthorize("hasAnyAuthority('permission:query','menu:query')")
    public ResultJson getInfo(@PathVariable Object id) {
        SystemPermissionMenuEntity result = systemPermissionMenuService.getById(id.toString());
        return ResponseResult.resultSuccess(result);
    }


    /**
     * <p>
     * 获取全部
     * </p>
     *
     * @param ids iDS
     * @return 结果集
     */
    @PostMapping("/permission/menu")
    public ResultJson getPermissionMenuByIds(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResponseResult.resultSuccess(null);
        }
        List<SystemPermissionMenuEntity> results = systemPermissionMenuService.listByIds(ids);
        return ResponseResult.resultSuccess(results);
    }

    /**
     * <p>
     * 根据权限ID获取
     * </p>
     *
     * @param permissionId 权限id
     * @return 结果集
     */
    @GetMapping("/permission/{permissionId}")
    public ResultJson getPermissionMenuByPermissionId(@PathVariable Long permissionId) {
        SystemPermissionMenuEntity entity = new SystemPermissionMenuEntity();
        entity.setPermissionId(permissionId);
        QueryWrapper<SystemPermissionMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(entity);
        List<SystemPermissionMenuEntity> results = systemPermissionMenuService.list(queryWrapper);
        return ResponseResult.resultSuccess(results);
    }

    /**
     * <p>
     * 根据菜单id获取
     * </p>
     *
     * @param menuId 菜单id
     * @return 结果集
     */
    @GetMapping("/menu/{menuId}")
    public ResultJson getPermissionMenuByMenuId(@PathVariable Long menuId) {
        SystemPermissionMenuVO permissionMenuVO = new SystemPermissionMenuVO();
        permissionMenuVO.setMenuId(menuId);
        List<PermissionMenuVO> results = systemPermissionMenuService.getPermissionMenuByMenuId(permissionMenuVO);
        return ResponseResult.resultSuccess(results);
    }

    /**
     * <p>
     * 解除权限与菜单绑定
     * </p>
     *
     * @param permissionId 权限id
     * @param menuId       菜单id
     * @return 是否成功
     */
    @GetMapping("/unBinding/{permissionId}/{menuId}")
    @PreAuthorize("hasAnyAuthority('permission:remove',hasAnyAuthority('menu:remove'))")
    public ResultJson unBinding(@PathVariable Long permissionId, @PathVariable Long menuId) {
        systemPermissionMenuService.unBinding(permissionId, menuId);
        return ResponseResult.resultSuccess("解绑成功");
    }

    /**
     * <p>
     * 获取所有菜单权限
     * </P>
     *
     * @return 菜单权限(树形结构)
     */
    @GetMapping("/getAll")
    public ResultJson getAllPermissionMenu() {
        List<PermissionMenuListVO> result = systemPermissionMenuService.getAllPermissionMenu();
        return ResponseResult.resultSuccess(result);
    }

    /**
     * <p>
     * 根据权限id获取菜单id
     * </p>
     *
     * @param permissionIds 权限id
     * @return 根据权限获取所有的菜单id(非树形结构)
     */
    @PostMapping("/menus")
    public ResultJson getMenuByPermission(@RequestBody List<Long> permissionIds) {
        if (CollectionUtils.isEmpty(permissionIds)) {
            return ResponseResult.resultSuccess(Lists.newArrayList());
        }
        SystemPermissionMenuEntity entity = new SystemPermissionMenuEntity();
        entity.setIsEnabled(1);
        QueryWrapper<SystemPermissionMenuEntity> queryWrapper = new QueryWrapper<>(entity);
        Set<Long> menuIds = Sets.newConcurrentHashSet();
        permissionIds.forEach(permissionId -> {
            entity.setPermissionId(permissionId);
            queryWrapper.setEntity(entity);
            List<SystemPermissionMenuEntity> list = systemPermissionMenuService.list(queryWrapper);
            if (!CollectionUtils.isEmpty(list)) {
                menuIds.addAll(list.parallelStream().map(SystemPermissionMenuEntity::getMenuId).collect(Collectors.toSet()));
            }
        });
        return ResponseResult.resultSuccess(Lists.newArrayList(menuIds));
    }
}

