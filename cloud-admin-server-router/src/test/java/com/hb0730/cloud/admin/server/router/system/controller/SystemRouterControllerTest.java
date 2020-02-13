package com.hb0730.cloud.admin.server.router.system.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SystemRouterControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void routers() throws Exception {
        String url="/admin/system/router/routers";
        MvcResult mvcResult  = mvc.perform(MockMvcRequestBuilders.get(url)
                .header("token", "7F573B177A8A4F7CA4E379EF1F18F603")
        ).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
    }
}
