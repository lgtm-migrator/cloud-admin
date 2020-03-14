package com.hb0730.cloud.admin.server.dept.system.service.impl;

import com.hb0730.cloud.admin.server.dept.system.service.ISystemDeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SystemDeptServiceImplTest {
    @Autowired
    private ISystemDeptService service;
    @Test
    public void getTree() {
        service.getTree();
    }
}
