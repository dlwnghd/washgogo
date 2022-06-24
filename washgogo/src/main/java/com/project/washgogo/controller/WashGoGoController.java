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
    public void index(){

    }

    @GetMapping("/guide")
    public void guide(){

    }

    @GetMapping("/pricesheet")
    public void pricesheet(){

    }

    @GetMapping("/life")
    public void life(){

    }

    @GetMapping("/requestGuide")
    public void requestGuide(){

    }

    @GetMapping("/requestSelect")
    public void requestSelect(){

    }

    @PostMapping("/requestSelect")
    public void requestSelectOK(){

    }



}