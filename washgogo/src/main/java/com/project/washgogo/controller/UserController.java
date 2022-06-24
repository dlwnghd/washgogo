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
    public String myPage(UserVO userVO){
        log.info(userVO.toString());
        return "/user/myPage";
    }

    @GetMapping("notice")
    public void notice(){

    }

    @GetMapping("point")
    public String point(UserVO userVO) {
        log.info(userVO.toString());
        return "/user/point";
    }


    @GetMapping("modifyingInformation")
    public String modifyingInformation(UserVO userVO){
        return "/user/modifyingInformation";
    }

    @GetMapping("useService")
    public String useService(UserVO userVO){
        return "/user/useService";
    }

    @GetMapping("serviceChange")
    public String serviceChange(UserVO userVO){
        return "/user/serviceChange";
    }

    @GetMapping("changeCancel")
    public String changeCancel(UserVO userVO){
        return "/user/changeCancel";
    }

    @GetMapping("paymentDetails")
    public String paymentDetails(OrderVO order) {
        return "/user/paymentDetails";
=======

//    로그인 / 회원가입
    @GetMapping("/login")
    public String login(){
        return "/user/login";
    }

    @PostMapping("/login")
    public String loginOK(UserVO userVO){
        log.info("---------------------");
        log.info(userVO.toString());
        log.info("---------------------");
        return "/user/login";
    }

    @GetMapping("/join")
    public String join(){
        return "/user/join";
    }

    @PostMapping("/join")
    public String joinOK(){
        return "/user/join";
    }

    @GetMapping("/findIdPw")
    public String findIdPw(){
        return "/user/findIdPw";
    }

    @PostMapping("/findIdPw")
    public String findIdPwOK(){
        return "/user/findIdPw";
    }

    @GetMapping("/resetPw")
    public String resetPw(){
        return "/user/resetPw";
    }

    @PostMapping("/resetPw")
    public String resetPwOK(){
        return "/user/resetPw";
    }
    
//    마이페이지
    @GetMapping("/mypage")
    public String mypage(){

        return "/user/mypage";
    }

    @GetMapping("/point")
    public String point(){

        return "/user/point";
    }

    @GetMapping("/paymentDetails")
    public String payment_details(){

        return "/user/paymentDetails";
    }

    @GetMapping("/notice")
    public String notice(){

        return "/user/notice";
    }

    @GetMapping("/useService")
    public String use_service(){

        return "/user/useService";
    }

    @GetMapping("/serviceChange")
    public String service_change(){

        return "/user/serviceChange";
    }

    @GetMapping("/modifyingInformation")
    public String modifying_information(){

        return "/user/modifyingInformation";
    }

    @PostMapping("/modifyingInformation")
    public String modifying_informationOK(){

        return "/user/modifyingInformation";
    }

    @PostMapping("/serviceChange")
    public String service_changeOK(){

        return "/user/serviceChange";
    }

    @GetMapping("/changeCancel")
    public String change_cancel(){

        return "/user/changeCancel";
>>>>>>> b699fcfbe5916a188310e11fb673010c30f487ad
    }
}
