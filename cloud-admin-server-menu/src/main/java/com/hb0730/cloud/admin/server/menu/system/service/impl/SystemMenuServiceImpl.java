package com.hb0730.cloud.admin.server.menu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.common.exception.NullPointerException;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.menu.system.mapper.SystemMenuMapper;
import com.hb0730.cloud.admin.server.menu.system.model.entity.SystemMenuEntity;
import com.hb0730.cloud.admin.server.menu.system.model.vo.MenuVO;
import com.hb0730.cloud.admin.server.menu.system.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Date;
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
    @Transactional(rollbackFor = {Exception.class})
    public boolean save(SystemMenuEntity entity) {
        entity = fillEntity(entity);
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean removeById(Serializable id) {
        //删除子集和本身
        return super.removeById(id);
    }

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

    private SystemMenuEntity fillEntity(SystemMenuEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        Long parentId = entity.getParentId();
        if (Objects.isNull(parentId)) {
            entity.setIsRoot(1);
            entity.setLevel(1);
            entity.setHasChild(0);
        } else {
            if (0 == parentId || -1 == parentId) {
                entity.setLevel(1);
                entity.setIsRoot(1);
                entity.setHasChild(0);
            } else {
                SystemMenuEntity parentEntity = menuMapper.selectById(parentId);
                if (Objects.isNull(parentEntity)) {
                    throw new NullPointerException("根据id" + parentId + "获取父级菜单失败");
                } else {
                    entity.setLevel(parentEntity.getLevel() + 1);
                    entity.setIsRoot(0);
                    entity.setHasChild(0);
                    if (parentEntity.getHasChild() == 0) {
                        SystemMenuEntity menuEntity = new SystemMenuEntity();
                        menuEntity.setHasChild(1);
                        menuEntity.setId(parentEntity.getId());
                        menuEntity.setUpdateTime(new Date());
                        menuEntity.setUpdateUserId(entity.getCreateUserId());
                        menuMapper.updateById(menuEntity);
                    }
                }
            }
        }
        entity.setHasChild(1);
        return entity;
    }
}
