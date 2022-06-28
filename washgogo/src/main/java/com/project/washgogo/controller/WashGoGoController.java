package com.project.washgogo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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


    @GetMapping("requestGuide")
    public String requestGuide() {
        return "/order/requestGuide";
    }

    @GetMapping("requestSelect")
    public String requestSelect() {
        return "/order/requestSelect";
    }

    @PostMapping("/requestSelect")
    public void requestSelectOK() {

    }

    @GetMapping("serviceDetail")
    public String serviceDetail(){
        return "/service/serviceDetail";
    }

    @GetMapping("serviceSubscribeAddress")
    public String serviceSubscribeAddress(){
        return "/service/serviceSubscribeAddress";
    }

    @GetMapping("serviceSubscribePayment")
    public String serviceSubscribePayment(){
        return "/service/serviceSubscribePayment";
    }

//    public void requestSelectOK(){
//
//    }

    @GetMapping("used")
    public String used(){
        return "/used";
    }



}