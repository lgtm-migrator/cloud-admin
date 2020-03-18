package com.hb0730.cloud.admin.server.menu.system.service.impl;

import com.hb0730.cloud.admin.server.menu.system.service.ISystemMenuService;
import org.assertj.core.util.Lists;
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
public class SystemMenuServiceImplTest {
    @Autowired
    private ISystemMenuService systemMenuService;

    @Test
    public void getThreeMenus() {
        systemMenuService.getThreeMenus();
    }

    @Test
    public void getChildrenId() {
        Set<Long> childrenId = systemMenuService.getChildrenId(0L);
        childrenId.remove(0L);
    }

    @Test
    public void removeById() {
        systemMenuService.removeById(0);
    }

    @Test
    public void getTree() {
        List<Long> ids = Lists.list(1239376788206804994L, 1232536626407628802L, 1239376133786329090L);
        List<Map<String, Object>> tree = systemMenuService.getVueTree(ids);
        System.out.println(tree);
    }
}
