package com.neo.web;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    @Test
    public void getUsers() throws Exception {
        String json = "{\n" +
                "    \"filterDimension\":{\n" +
                "        \"operator\":\"and\",\n" +
                "        \"filters\":[\n" +
                "            {\n" +
                "                \"field\":\"author_user_id\",\n" +
                "                \"operator\":\"=\",\n" +
                "                \"value\":\"51881489079\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"business\":\"news_article_13\",\n" +
                "    \"metricList\":[\n" +
                "        \"acc_total_income\",\n" +
                "        \"acc_basic_income\",\n" +
                "        \"acc_subsidy\"\n" +
                "    ],\n" +
                "    \"date\":{\n" +
                "        \"startTime\":\"2021-01-20\",\n" +
                "        \"endTime\":\"2021-01-20\"\n" +
                "    }\n" +
                "}";
        System.out.println();
//        mockMvc.perform(MockMvcRequestBuilders.post("/getUsers")
//                .accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
    }

}