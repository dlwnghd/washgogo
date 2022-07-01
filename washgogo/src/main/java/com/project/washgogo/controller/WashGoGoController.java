package com.project.washgogo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/*")
@Slf4j
public class WashGoGoController {

    @GetMapping("index")
    public void index() {
    }

    @GetMapping("guide")
    public String guide() {
        return "/guide/guide";
    }

    @GetMapping("priceSheet")
    public String priceSheet() {
        return "/guide/priceSheet";
    }

    @GetMapping("life")
    public String life() {
        return "/guide/life";
    }

//    public void requestSelectOK(){
//
//    }

    @GetMapping("used")
    public String used(){
        return "/user/used";
    }



}