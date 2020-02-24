package com.hb0730.cloud.admin.server.menu.system.service;

import com.hb0730.cloud.admin.server.menu.system.model.entity.SystemMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.cloud.admin.server.menu.system.model.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 系统菜单  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
public interface ISystemMenuService extends IService<SystemMenuEntity> {

    /**
     * <p>
     * 获取树形的菜单
     * </p>
     *
     * @return
     */
    List<MenuVO> getThreeMenus();
}
