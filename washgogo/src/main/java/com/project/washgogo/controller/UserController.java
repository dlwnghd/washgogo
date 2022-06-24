package com.project.washgogo.controller;

import com.project.washgogo.domain.dao.UserDAO;
import com.project.washgogo.domain.vo.OrderVO;
import com.project.washgogo.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
@Slf4j
public class UserController {
    @GetMapping("myPage")
    public void myPage(UserVO userVO){

    }

    @GetMapping("notice")
    public void notice(){

    }

    @GetMapping("point")
    public void point(UserVO userVO) {
        log.info(userVO.toString());
    }


    @GetMapping("modifyingInformation")
    public void modifyingInformation(UserVO userVO){

    }

    @GetMapping("useService")
    public void useService(UserVO userVO){}

    @GetMapping("serviceChange")
    public void serviceChange(UserVO userVO){

    }

    @GetMapping("changeCancel")
    public void changeCancel(UserVO userVO){

    }

    @GetMapping("paymentDetails")
    public void paymentDetails(OrderVO order) {

    }
}
