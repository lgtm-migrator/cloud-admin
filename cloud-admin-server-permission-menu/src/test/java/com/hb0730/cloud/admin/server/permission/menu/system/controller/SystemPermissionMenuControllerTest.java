package com.hb0730.cloud.admin.server.permission.menu.system.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.hb0730.cloud.admin.common.util.RequestMappingConstants.PERMISSION_MENU_SERVER_REQUEST;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SystemPermissionMenuControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void getAllPermissionMenu() throws Exception {
        String url=PERMISSION_MENU_SERVER_REQUEST+"/getAll";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(url).header("authorization", "Bearer 636b2eab-24b2-4407-978b-dc0610fb4c8d").contentType(MediaType.APPLICATION_JSON)).andReturn();
        String contentAsString = result.getResponse().getContentAsString();
    }
}
