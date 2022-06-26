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

    @GetMapping("/login")
    public void login(){

    }

    @PostMapping("/login")
    public void loginOK(){
    }

    @GetMapping("/join")
    public void join(){

    }

    @PostMapping("/join")
    public void joinOK(){
    }

    @GetMapping("/findIdPw")
    public void findIdPw(){

    }

    @PostMapping("/findIdPw")
    public void findIdPwOK(){

    }

    @GetMapping("/resetPw")
    public void resetPw(){

    }

    @PostMapping("/resetPw")
    public void resetPwOK(){

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

    @GetMapping("/mypage")
    public void mypage(){

    }

    @GetMapping("/point")
    public void point(){

    }

    @GetMapping("/payment_details")
    public void payment_details(){

    }

    @GetMapping("/notice")
    public void notice(){

    }

    @GetMapping("/use_service")
    public void use_service(){

    }

    @GetMapping("/service_change")
    public void service_change(){

    }

    @GetMapping("/modifying_information")
    public void modifying_information(){

    }

    @PostMapping("/modifying_information")
    public void modifying_informationOK(){

    }

    @PostMapping("/service_change")
    public void service_changeOK(){

    }

    @GetMapping("/change_cancel")
    public void change_cancel(){

    }
}