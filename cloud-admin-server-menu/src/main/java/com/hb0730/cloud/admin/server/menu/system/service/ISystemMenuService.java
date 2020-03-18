package com.hb0730.cloud.admin.server.menu.system.service;

import com.hb0730.cloud.admin.server.menu.system.model.entity.SystemMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.cloud.admin.server.menu.system.model.vo.MenuVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
     * 根据id删除
     * </p>
     *
     * @param entity entity
     * @return 是否成功
     */
    boolean removeById(SystemMenuEntity entity);

    /**
     * <p>
     * 获取树形的菜单
     * </p>
     *
     * @return 全部树形菜单
     */
    List<MenuVO> getThreeMenus();

    /**
     * <p>
     * 根据id获取子集id(包含本id)
     * </p>
     *
     * @param id id
     * @return id集
     */
    Set<Long> getChildrenId(Long id);

    /**
     * <p>
     * 获取树形菜单
     * </p>
     *
     * @param menuIds 菜单id
     * @return 树形菜单
     */
    List<Map<String, Object>> getVueTree(List<Long> menuIds);
}
