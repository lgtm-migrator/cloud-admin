package com.hb0730.cloud.admin.server.menu.system.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hb0730.cloud.admin.server.menu.system.model.entity.SystemMenuEntity;
import com.hb0730.cloud.admin.server.menu.system.model.vo.MenuVO;
import com.hb0730.cloud.admin.server.menu.system.service.ISystemMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MenuUtilsTest {
    @Autowired
    private ISystemMenuService systemMenuService;

    @Test
    public void getParentInfoByChildrenId() {
        SystemMenuEntity entity = new SystemMenuEntity();
        entity.setIsEnabled(1);
        QueryWrapper<SystemMenuEntity> queryWrapper = new QueryWrapper<>(entity);
        List<SystemMenuEntity> allMenu = systemMenuService.list(queryWrapper);
        SystemMenuEntity childrenEntity = new SystemMenuEntity();
        childrenEntity.setId(1239375199958097922L);
        childrenEntity.setParentId(1232535754990641154L);
        Set<Long> menuIds = Sets.newConcurrentHashSet();
        MenuUtils.getParentNodeInfoByChildrenNode(childrenEntity, allMenu, menuIds);
        System.out.println("完整节点(通过某一节点上下)" + menuIds);
//        childrenEntity.setId(1239376788206804994L);
//        childrenEntity.setParentId(1239376133786329090L);
//        MenuUtils.getParentNodeInfoByChildrenNode(childrenEntity, allMenu, menuIds);
//        childrenEntity.setId(1239416908121268226L);
//        MenuUtils.getParentNodeInfoByChildrenNode(childrenEntity, allMenu, menuIds);
//        System.out.println(menuIds);
//        List<MenuVO> vueModelTreeByMenuId = MenuUtils.getTreeByNodeId(MenuUtils.getMenusTreeByParentId(0L), menuIds);
//        System.out.println(vueModelTreeByMenuId);
//        List<Map<String,Object>> maps=Lists.newArrayList();
//        List<Map<String, Object>> vueModel = MenuUtils.getVueModel(vueModelTreeByMenuId, maps);
//        System.out.println(vueModel);
    }

    @Test
    public void getVueModel() {
    }
}
