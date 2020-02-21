package com.hb0730.cloud.admin.server.menu.system.controller;


import com.hb0730.cloud.admin.common.web.controller.AbstractBaseController;
import com.hb0730.cloud.admin.common.web.response.ResultJson;
import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import com.hb0730.cloud.admin.commons.model.security.UserDetail;
import com.hb0730.cloud.admin.server.menu.system.model.entity.SystemMenuEntity;
import com.hb0730.cloud.admin.server.menu.system.service.ISystemMenuService;
import com.hb0730.cloud.admin.server.menu.utils.SecurityContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
public class SystemMenuController extends AbstractBaseController<SystemMenuEntity> {

    @Autowired
    private ISystemMenuService systemMenuService;

    @PostMapping("/save")
    @Override
    public ResultJson save(@RequestBody SystemMenuEntity target) {
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
        systemMenuService.save(target);
        return ResponseResult.resultSuccess("保存成功");
    }

    @Override
    public ResultJson delete(Object id) {
        return null;
    }

    @Override
    public ResultJson submit(SystemMenuEntity target) {
        return null;
    }

    @GetMapping("/menu/{id}")
    @Override
    public ResultJson gitObject(@PathVariable Object id) {
        SystemMenuEntity result = systemMenuService.getById(id.toString());
        return ResponseResult.resultSuccess(result);
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
}

