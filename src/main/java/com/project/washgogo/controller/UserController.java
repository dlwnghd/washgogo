package com.project.washgogo.controller;

import com.project.washgogo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final NoticeService noticeService;
    @GetMapping("noticeAdd")
    public String noticeAdd(){
        return "/user/noticeAdd";
    };


    @GetMapping("notice")
    public String getList() {
        log.info("----------------------------");
        log.info("ok............. : ");
        log.info("----------------------------");
        return "/user/notice";
    }
}
