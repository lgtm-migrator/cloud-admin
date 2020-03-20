package com.hb0730.cloud.admin.server.menu.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.cloud.admin.api.feign.permission.menu.handler.PermissionMenuHandler;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.CodeStatusEnum;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.commons.model.security.permission.SystemPermissionVO;
import com.hb0730.cloud.admin.server.menu.system.model.entity.SystemMenuEntity;
import com.hb0730.cloud.admin.server.menu.system.model.vo.MenuVO;
import com.hb0730.cloud.admin.server.menu.system.model.vo.SystemMenuVO;
import com.hb0730.cloud.admin.server.menu.system.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.MENU_SERVER_REQUEST;

/**
 * <p>
 * 系统菜单  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@RestController
@RequestMapping(MENU_SERVER_REQUEST)
public class SystemMenuController extends AbstractBaseController<SystemMenuVO> {

    @Autowired
    private ISystemMenuService systemMenuService;
    @Autowired
    private PermissionMenuHandler permissionMenuHandler;

    @PostMapping("/save")
    @Override
    @PreAuthorize("hasAnyAuthority('menu:save')")
    public ResultJson save(@RequestBody SystemMenuVO target) {
        target.setCreateUserId(getCurrentUser().getId());
        target.setCreateTime(new Date());
        target.setVersion(1);
        target.setIsEnabled(1);
        SystemMenuEntity entity = BeanUtils.transformFrom(target, SystemMenuEntity.class);
        systemMenuService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
    }

    @Override
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('menu:remove')")
    public ResultJson delete(@PathVariable Object id) {
        SystemMenuEntity entity = new SystemMenuEntity();
        entity.setUpdateUserId(getCurrentUser().getId());
        entity.setUpdateTime(new Date());
        entity.setId(new Long(id.toString()));
        systemMenuService.removeById(entity);
        return ResponseResult.resultSuccess("删除成功");
    }

    @Override
    public ResultJson submit(SystemMenuVO target) {
        return null;
    }

    @GetMapping("/{id}")
    @Override
    public ResultJson getInfo(@PathVariable Object id) {
        SystemMenuEntity result = systemMenuService.getById(id.toString());
        SystemMenuVO menuVO = BeanUtils.transformFrom(result, SystemMenuVO.class);
        return ResponseResult.resultSuccess(menuVO);
    }

    /**
     * <p>
     * 获取菜单根据父类id
     * </p>
     *
     * @param parentId 父类id
     * @return 菜单
     */
    @GetMapping("/menu/{parentId}")
//    @PreAuthorize("hasAnyAuthority('menu:query')")
    public ResultJson getMenusByParentId(@PathVariable Long parentId) {
        SystemMenuEntity entity = new SystemMenuEntity();
        entity.setParentId(parentId);
        QueryWrapper<SystemMenuEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemMenuEntity> entityList = systemMenuService.list(queryWrapper);
        List<SystemMenuVO> menuVOList = BeanUtils.transformFromInBatch(entityList, SystemMenuVO.class);
        return ResponseResult.resultSuccess(menuVOList);
    }

    /**
     * <p>
     * 根据批量id获取
     * </P>
     *
     * @return 菜单集
     */
    @PostMapping("/menus")
    public ResultJson getMenus(@RequestBody List<Long> id) {
        if (CollectionUtils.isEmpty(id)) {
            return ResponseResult.resultSuccess(null);
        }
        List<SystemMenuEntity> results = systemMenuService.listByIds(id);
        return ResponseResult.resultSuccess(results);
    }

    /**
     * <p>
     * 获取全部的菜单
     * </p>
     *
     * @return 菜单集
     */
    @GetMapping("/menus")
    public ResultJson getMenus() {
        List<SystemMenuEntity> result = systemMenuService.list();
        return ResponseResult.resultSuccess(result);
    }

    /**
     * <p>
     * 获取树形菜单id
     * </p>
     *
     * @return 菜单
     */
    @GetMapping("/menus/tree/{type}")
    @PreAuthorize("hasAnyAuthority('menu:query')")
    public ResultJson getMenusTree(@PathVariable Integer type) {
        if (type == 1) {
            return null;
        } else {
            return getMenusTreeAll();
        }
    }

    /**
     * <p>
     * 根据id更新
     * </p>
     *
     * @param id id
     * @param vo 更新参数
     * @return 是否成功
     */
    @PostMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('menu:update')")
    public ResultJson updateById(@PathVariable Long id, @RequestBody SystemMenuVO vo) {
        UserDetail currentUser = getCurrentUser();
        if (Objects.isNull(currentUser)) {
            return ResponseResult.result(CodeStatusEnum.NON_LOGIN, "获取当前用户失败");
        }
        vo.setUpdateTime(new Date());
        vo.setUpdateUserId(currentUser.getId());
        SystemMenuEntity entity = systemMenuService.getById(id);
        if (Objects.isNull(entity)) {
            return ResponseResult.resultFall("根据id获取菜单失败,请检查");
        }
        BeanUtils.updateProperties(vo, entity);
        systemMenuService.updateById(entity);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 获取前端vue所需结构类型的树形菜单
     * </P>
     *
     * @param menuIds 菜单id
     * @return vue结构菜单
     */
    @PostMapping("/vueMenuTree")
    public ResultJson getVueMenuTree(@RequestBody List<Long> menuIds) {
        List<Map<String, Object>> vueTree = systemMenuService.getVueTreeByMenuId(menuIds);
        return ResponseResult.resultSuccess(vueTree);
    }

    /**
     * <p>
     * 根据当前用户获vue树形菜单
     * </p>
     *
     * @return vue菜单
     */
    @GetMapping("/vueMenuTree/current")
    public ResultJson getVueMenuByCurrentUser() {
        UserDetail currentUser = getCurrentUser();
        List<SystemPermissionVO> userPermission = currentUser.getUserPermission();
        List<Long> permissionId = userPermission.parallelStream().map(SystemPermissionVO::getId).collect(Collectors.toList());
        List<Long> menuIds = permissionMenuHandler.getMenuIdByPermissionId(permissionId);
        List<Map<String, Object>> vueTreeByMenuId = systemMenuService.getVueTreeByMenuId(menuIds);
        return ResponseResult.resultSuccess(vueTreeByMenuId);
    }

    /**
     * <p>
     * 获取全部树形菜单
     * </p>
     *
     * @return 树形菜单
     */
    private ResultJson getMenusTreeAll() {
        List<MenuVO> threeMenus = systemMenuService.getThreeMenus();
        return ResponseResult.resultSuccess(threeMenus);
    }

}

