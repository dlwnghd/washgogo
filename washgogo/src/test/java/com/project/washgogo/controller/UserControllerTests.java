package com.project.washgogo.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
public class UserControllerTests {
    //    마치 브라우저에서 URL을 요청한 것처럼 환경을 만들어준다.
    private MockMvc mockMvc;

    @Autowired
//    요청을 처리해주는 WebApplicationContext를 불러온다.
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    @Test
//    public void loginTest() throws Exception{
//        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/user/login")).andReturn().getModelAndView().getModelMap().toString());
//    }

//    @Test
//    public void loginTest() throws Exception{
//        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
//                .param("userEmail","alion0513@naver.com")
//                .param("userPw","1234")).andReturn().getModelAndView().toString());
//    }

}
