package com.hb0730.cloud.admin.server.menu.system.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hb0730.cloud.admin.common.util.BeanUtils;
import com.hb0730.cloud.admin.server.menu.system.mapper.SystemMenuMapper;
import com.hb0730.cloud.admin.server.menu.system.model.entity.SystemMenuEntity;
import com.hb0730.cloud.admin.server.menu.system.model.vo.MenuVO;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class MenuUtils {
    /**
     * 获取mapper
     *
     * @return mapper
     */
    private static SystemMenuMapper getMapper() {
        return SpringContextUtil.getBean(SystemMenuMapper.class);
    }

    /**
     * <p>
     * 通过父节点id获取子节点(树形菜单)
     * </p>
     *
     * @param id 父节点id
     * @return 树形菜单
     */
    public static List<MenuVO> getMenusTreeByParentId(@NonNull Long id) {
        List<MenuVO> menus = Lists.newArrayList();
        List<SystemMenuEntity> menuEntities = getMenusByParentId(id);
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

    /**
     * 根据父节点获取子节点信息(不包含子子级)
     *
     * @param parentId 父节点id
     * @return 子节点信息
     */
    private static List<SystemMenuEntity> getMenusByParentId(Long parentId) {
        SystemMenuEntity entity = new SystemMenuEntity();
        if (Objects.isNull(parentId)) {
            entity.setParentId(null);
        } else {
            entity.setParentId(parentId);
        }
        QueryWrapper<SystemMenuEntity> queryWrapper = new QueryWrapper<>(entity);
        return getMapper().selectList(queryWrapper);
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
    private static MenuVO getChildes(MenuVO vo, List<MenuVO> menus) {
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

    /**
     * <p>
     * 根据节点id获取顶级菜单信息
     * </p>
     *
     * @param entity  子节点信息
     * @param allMenu 全部已启用的菜单
     * @param ids     节点id(用于装所有父节点id)
     * @return 顶级菜单信息
     */
    public static SystemMenuEntity getParentNodeInfoByChildrenNode(SystemMenuEntity entity, List<SystemMenuEntity> allMenu, Set<Long> ids) {
        ids.add(entity.getId());
        if (entity.getParentId() == -1 || entity.getParentId() == 0) {
            return entity;
        }
        SystemMenuEntity parentEntity = allMenu.parallelStream().filter(e1 -> Objects.equals(e1.getId(), entity.getParentId())).findFirst().get();
        return getParentNodeInfoByChildrenNode(parentEntity, allMenu, ids);
    }

    /**
     * <p>
     * 根据节点id过滤树形
     * </p>
     *
     * @param menus 树形菜单
     * @param ids   节点id(完整的节点信息(a>b>c>d))
     * @return 过滤后的树形
     */
    public static List<MenuVO> getTreeByNodeId(List<MenuVO> menus, Set<Long> ids) {
        List<MenuVO> collect = menus.stream().filter(e1 -> ids.contains(e1.getId())).collect(Collectors.toList());
        collect.forEach(t -> getChildren(t, ids));
        return collect;
    }

    /**
     * 根据id过滤树形
     *
     * @param vo  树形菜单
     * @param ids 节点id
     */
    private static void getChildren(MenuVO vo, Set<Long> ids) {
        List<MenuVO> childrens = vo.getChildrens();
        if (CollectionUtils.isEmpty(childrens)) {
            return;
        }
        List<MenuVO> collect = childrens.stream().filter(e1 -> ids.contains(e1.getId())).collect(Collectors.toList());
        vo.setChildrens(getTreeByNodeId(collect, ids));
    }

    /**
     * <p>
     * 将树形vo转换vue 菜单所需格式<br>
     * {@code [
     * {"title":"",
     * "path":"",
     * "icon":"",
     * "children":[]
     * },
     * {"title":"",
     * "path":"",
     * "icon":"",
     * }
     * ]
     * }
     * </p>
     *
     * @param menus 树形菜单
     * @param maps  空参数
     * @return 树形结构map格式
     */
    public static List<Map<String, Object>> getVueModel(List<MenuVO> menus, List<Map<String, Object>> maps) {
        if (CollectionUtils.isEmpty(menus)) {
            return Lists.newArrayList();
        }
        menus.forEach(menu -> {
            Map<String, Object> map = Maps.newHashMap();
            map.put("title", menu.getName());
            map.put("path", menu.getUrl());
            map.put("icon", menu.getIcon());
            List<Map<String, Object>> mapsList = Lists.newArrayList();
            List<Map<String, Object>> vueModel = getVueModel(menu.getChildrens(), mapsList);
            if (!CollectionUtils.isEmpty(vueModel)) {
                map.put("children", vueModel);
            }
            maps.add(map);
        });
        return maps;
    }
}
