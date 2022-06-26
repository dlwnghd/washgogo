package com.project.washgogo.controller;

import com.project.washgogo.domain.dao.UserDAO;
import com.project.washgogo.domain.vo.OrderVO;
import com.project.washgogo.domain.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
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

    @PostMapping("modifyingInformation")
    public String modifyingInformationOK(UserVO userVO){
        return "/user/myPage";
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
    }

//    ###로그인 / 회원가입###
//    회원가입 페이지로 이동
    @GetMapping("join")
    public String join(){
        log.info("--------join/Get---------");
        return "/user/join";
    }
//    회원가입 여부
    @PostMapping("join")
    public String joinOK(){
        log.info("--------joinOK/Post---------");
//        log.info(userVO.toString());
        log.info("---------------------");
        return "/user/login";
    }

//    로그인 페이지로 이동
    @GetMapping("login")
    public String loginOK(){
        log.info("---------------------");
        log.info("---loginGetMapping---");
        log.info("---------------------");
        return "/user/login";
    }

//    로그인 여부
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(UserVO userVO, Model model, String Email, String Pw){
        log.info("---------------------");
        log.info("---loginPostMapping---");
        log.info("이메일 : " + Email); // 입력한 아이디
        log.info("비밀번호 : " + Pw);   // 입력한 비밀번호
        log.info(userVO.getUserEmail());
        log.info(userVO.getUserPw());
        log.info("---------------------");
        if(userVO.getUserEmail().equals(Email)){
            if(userVO.getUserPw().equals(Pw)){
                model.addAttribute("email",userVO.getUserEmail());
                log.info("---로그인 성공---");
                return "/index";
            }
        }
        log.info("---로그인 실패---");
        return "/user/login";
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
}
