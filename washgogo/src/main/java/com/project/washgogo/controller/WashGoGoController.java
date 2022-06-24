package com.project.washgogo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
@Slf4j
public class WashGoGoController {
    @GetMapping("/index")
    public void index() {

    }

    @GetMapping("/guide")
    public void guide() {

    }

    @GetMapping("/priceSheet")
    public void priceSheet() {

    }

    @GetMapping("/life")
    public void life() {

    }


    @GetMapping("/requestGuide")
    public void requestGuide() {

    }

    @GetMapping("/requestSelect")
    public void requestSelect() {

    }

    @PostMapping("/requestSelect")
    public void requestSelectOK() {

    }

    @GetMapping("/serviceDetail")
    public String serviceDetail(){
        return "/service/serviceDetail";
    }

    @GetMapping("/serviceSubscribeAddress")
    public String serviceSubscribeAddress(){
        return "/service/serviceSubscribeAddress";
    }

    @GetMapping("/serviceSubscribePayment")
    public String serviceSubscribePayment(){
        return "/service/serviceSubscribePayment";
    }

//    public void requestSelectOK(){
//
//    }



}