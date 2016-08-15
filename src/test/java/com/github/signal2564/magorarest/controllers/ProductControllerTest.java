package com.github.signal2564.magorarest.controllers;

import com.github.signal2564.magorarest.config.Config;
import com.github.signal2564.magorarest.config.SecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Config.class, SecurityConfig.class})
public class ProductControllerTest {

    private static final String PREFIX = "/api/1/products";

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FilterChainProxy filterChain;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = webAppContextSetup(context).addFilters(filterChain).build();
        SecurityContextHolder.clearContext();
    }

    @Test
    public void allowGetRequestToRoot() throws Exception {
        mvc.perform(get(PREFIX + "/"))
                .andExpect(status().isOk());
    }

    @Test
    public void rejectPostRequestToRoot() throws Exception {
        mvc.perform(post(PREFIX + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"t\",\"price\":10.0}"))
                    .andExpect(status().isUnauthorized());
    }

}