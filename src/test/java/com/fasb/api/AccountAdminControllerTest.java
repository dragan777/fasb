package com.fasb.api;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@SpringBootTest
@AutoConfigureMockMvc
public class AccountAdminControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "spring")
    public void shouldReturnEmptyValue() throws Exception {
        this.mockMvc.perform(get("/api/v1/accounts")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
    }

//WRITE ANOTHER TESTS FOR CONTROLLERS

}
