package com.project.washgogo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @GetMapping("guideTab")
    public String guideTab(@ModelAttribute("tab") int tab) {
        log.info("-------------guideTab 컨트롤러 진입------------------");
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


}