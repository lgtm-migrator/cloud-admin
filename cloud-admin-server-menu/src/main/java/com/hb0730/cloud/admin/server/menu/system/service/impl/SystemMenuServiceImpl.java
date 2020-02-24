package com.hb0730.cloud.admin.server.menu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.menu.system.mapper.SystemMenuMapper;
import com.hb0730.cloud.admin.server.menu.system.model.entity.SystemMenuEntity;
import com.hb0730.cloud.admin.server.menu.system.model.vo.MenuVO;
import com.hb0730.cloud.admin.server.menu.system.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统菜单  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-02-19
 */
@Service
public class SystemMenuServiceImpl extends BaseServiceImpl<SystemMenuMapper, SystemMenuEntity> implements ISystemMenuService {

    @Autowired
    private SystemMenuMapper menuMapper;

    @Override
    public List<MenuVO> getThreeMenus() {
        List<MenuVO> menus = Lists.newArrayList();
        List<SystemMenuEntity> menuEntities = getMenusByParentId(0L);
        if (CollectionUtils.isEmpty(menuEntities)) {
            return menus;
        }
        List<MenuVO> voList = BeanUtils.transformFromInBatch(menuEntities, MenuVO.class);
        voList.forEach(menu -> {
            List<MenuVO> childrens = Lists.newArrayList();
            MenuVO childes = getChildes(menu, childrens);
            menus.add(childes);
        });
        return menus;
    }

    @Override
    public List<SystemMenuEntity> list() {
        return super.list();
    }

    /**
     * <p>
     * 根据父类id获取菜单
     * </p>
     *
     * @param parentId 父id
     * @return 菜单
     */
    private List<SystemMenuEntity> getMenusByParentId(Long parentId) {
        SystemMenuEntity entity = new SystemMenuEntity();
        if (Objects.isNull(parentId)) {
            entity.setParentId(null);
        } else {
            entity.setParentId(parentId);
        }
        QueryWrapper<SystemMenuEntity> queryWrapper = new QueryWrapper<>(entity);
        return menuMapper.selectList(queryWrapper);
    }

    /**
     * <p>
     * 获取子菜单
     * </p>
     *
     * @param vo    父菜单
     * @param menus 子集
     * @return 菜单
     */
    private MenuVO getChildes(MenuVO vo, List<MenuVO> menus) {
        List<SystemMenuEntity> systemMenuEntityList = getMenusByParentId(vo.getId());
        vo.setChildrens(menus);
        systemMenuEntityList.forEach(systemMenu -> {
            MenuVO menuVO = BeanUtils.transformFrom(systemMenu, MenuVO.class);
            if (!Objects.isNull(menuVO)) {
                List<MenuVO> voArrayList = Lists.newArrayList();
                MenuVO childes = getChildes(menuVO, voArrayList);
                menus.add(childes);
            }

        });
        return vo;
    }
}
