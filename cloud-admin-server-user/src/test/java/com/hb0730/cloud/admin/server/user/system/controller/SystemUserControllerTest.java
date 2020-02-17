package com.hb0730.cloud.admin.server.user.system.controller;

import com.alibaba.fastjson.JSON;
import com.hb0730.cloud.admin.server.user.system.model.entity.SystemUserEntity;
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

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.USER_SERVER_REQUEST;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SystemUserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void save() throws Exception {
        SystemUserEntity entity = new SystemUserEntity();
        entity.setLoginEmail("XXXX@qq.com");
        entity.setLogin("test2");
        entity.setLoginPasswd("1234");
        entity.setLoginName("测试");
        String json = JSON.toJSONString(entity);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(USER_SERVER_REQUEST + "/save").contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
    }

    @Test
    public void delete() {
    }

    @Test
    public void login() throws Exception {
        String login = "test2";
        String password = "1234";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(USER_SERVER_REQUEST + "/login").param("login", login).param("password", password)).andReturn();
        MockHttpServletResponse response = result.getResponse();
    }
}
