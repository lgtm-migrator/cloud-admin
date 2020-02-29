package com.hb0730.cloud.admin.server.permission.system.controller;

import com.alibaba.fastjson.JSON;
import com.hb0730.cloud.admin.server.permission.system.model.vo.PermissionMenuVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.PERMISSION_SERVER_REQUEST;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SystemPermissionControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void save() throws Exception {
        String url = PERMISSION_SERVER_REQUEST + "/save";
        PermissionMenuVO vo = new PermissionMenuVO();
        vo.setMenuId(1L);
        vo.setId(1L);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(JSON.toJSONString(vo)).header("authorization","Bearer 23ac6c93-41d8-47e0-8d17-6f34cfdae647")).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
    }
}
