package com.hb0730.cloud.admin.server.menu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.cloud.admin.commons.service.BaseServiceImpl;
import com.hb0730.cloud.admin.server.menu.system.mapper.SystemMenuMapper;
import com.hb0730.cloud.admin.server.menu.system.model.entity.SystemMenuEntity;
import com.hb0730.cloud.admin.server.menu.system.model.vo.MenuVO;
import com.hb0730.cloud.admin.server.menu.system.service.ISystemMenuService;
import com.hb0730.cloud.admin.server.menu.system.util.MenuUtils;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

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
        Object[] obj = {id};
        Long idLong = Long.valueOf(obj[0].toString());
        Set<Long> childrenId = getChildrenId(idLong);
        childrenId.remove(idLong);
        removeByIds(childrenId);
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(SystemMenuEntity entity) {
        Set<Long> childrenId = getChildrenId(entity.getId());
        childrenId.remove(entity.getId());
        //更新
        updateById(entity);
        //更新子集
        List<SystemMenuEntity> entityList = Lists.newArrayList();
        childrenId.forEach(id -> {
            SystemMenuEntity entity1 = new SystemMenuEntity();
            entity1.setUpdateTime(entity.getUpdateTime());
            entity1.setUpdateUserId(entity.getUpdateUserId());
            entity1.setId(id);
            entityList.add(entity1);
        });
        updateBatchById(entityList);
        //删除子集
        removeByIds(childrenId);
        //删除
        return super.removeById(entity.getId());
    }

    @Override
    public boolean updateById(SystemMenuEntity entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean updateBatchById(Collection<SystemMenuEntity> entityList) {
        return super.updateBatchById(entityList);
    }

    @Override
    public List<MenuVO> getThreeMenus() {
        return MenuUtils.getMenusTreeByParentId(0L);
    }

    @Override
    public List<SystemMenuEntity> list() {
        return super.list();
    }

    /**
     * <p>
     * 获取当前id及子集菜单id
     * </p>
     *
     * @param id id
     * @return 子集菜单
     */
    @Override
    public Set<Long> getChildrenId(Long id) {
        List<MenuVO> menuTree = MenuUtils.getMenusTreeByParentId(id);
        MenuVO tree = new MenuVO();
        tree.setId(id);
        tree.setChildrens(menuTree);
        Set<Long> ids = Sets.newHashSet();
        getChildrenId(tree, ids);
        return ids;
    }

    @Override
    public List<Map<String, Object>> getVueTreeByMenuId(List<Long> menuIds) {
        if (CollectionUtils.isEmpty(menuIds)) {
            return Lists.newArrayList();
        }
        Set<Long> menuIdsSet = com.google.common.collect.Sets.newHashSet(menuIds);
        Set<Long> ids = Sets.newHashSet();
        SystemMenuEntity e1 = new SystemMenuEntity();
        e1.setIsEnabled(1);
        QueryWrapper<SystemMenuEntity> queryWrapper = new QueryWrapper<>(e1);
        List<SystemMenuEntity> allMenu = list(queryWrapper);
        menuIdsSet.parallelStream().forEach(id -> {
            SystemMenuEntity entity = getById(id);
            MenuUtils.getParentNodeInfoByChildrenNode(entity, allMenu, ids);
        });
        List<MenuVO> menuTree = MenuUtils.getMenusTreeByParentId(0L);
        List<MenuVO> treeByNodeId = MenuUtils.getTreeByNodeId(menuTree, ids);
        List<Map<String, Object>> maps = Lists.newArrayList();
        MenuUtils.getVueModel(treeByNodeId, maps);
        return maps;
    }

    /**
     * 获取子集id
     *
     * @param vo  树形菜单
     * @param ids 容器
     */
    private static void getChildrenId(MenuVO vo, Set<Long> ids) {
        if (!org.springframework.util.StringUtils.isEmpty(vo)) {
            List<MenuVO> childrens = vo.getChildrens();
            ids.add(vo.getId());
            childrens.forEach(children -> {
                getChildrenId(children, ids);
            });
        }
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
