package com.project.washgogo.controller;


import com.project.washgogo.domain.vo.OrderVO;
import com.project.washgogo.domain.vo.UserVO;
import com.project.washgogo.service.UserService;
import com.project.washgogo.domain.vo.*;
import com.project.washgogo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final NoticeService noticeService;


    @GetMapping("myPage")
    public String myPage(UserVO userVO){
        log.info("-------------------------");
        log.info(userVO.toString());
        log.info("-------------------------");
        return "/user/myPage";
    }

    @GetMapping("noticeAdd")
    public void noticeAdd(){
    };

    @GetMapping("notice")
    public String getList(Criteria criteria, Model model){
        //log.info("----------------------------");
        //log.info("list............. : " + criteria);
        //log.info("----------------------------");

        model.addAttribute("noticeList", noticeService.getList(criteria));
        //model.addAttribute("pageDTO", new PageDTO(criteria, noticeService.getTotal(criteria)));
        return "/user/notice";
    }

    @PostMapping("add")
    public RedirectView add(NoticeVO noticeVO, RedirectAttributes rttr){
        log.info("----------------------------");
        log.info("register............. : " + noticeVO);
        log.info("----------------------------");

        noticeService.add(noticeVO);
//        1. Flash 사용
//         세션에 파라미터를 저장하고, request 객체가 초기화된 후 다시 request에 담아준다.
        rttr.addFlashAttribute("noticeNumber", noticeVO.getNoticeNumber());

//        2. 쿼리 스트링
//        rttr.addAttribute("boardNumber", boardVO.getBoardNumber());
        return new RedirectView("/user/notice");
    }

    @GetMapping("point")
    public String point(UserVO userVO) {
        log.info(userVO.toString());
        log.info(userVO.getUserPoint());
        return "/user/point";
    }


    @GetMapping("modifyingInformation")
    public String modifyingInformation(UserVO userVO){
        log.info(userVO.toString());
        return "/user/modifyingInformation";
    }

    @PostMapping("modifyingInformation")
    public String modifyingInformationOK(UserVO userVO){
        log.info(userVO.toString());
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

//    로그인 / 회원가입
    @GetMapping("/join")
    public String join(){
        log.info("--------join/Get---------");
        return "/user/join";
}

    @PostMapping("/join")
    public String joinOK(UserVO userVO){
        log.info("--------join/Post---------");
        log.info(userVO.toString());
        log.info("---------------------");
        return "/user/join";
    }

    @GetMapping("/login")
    public String login(UserVO userVO){
        log.info("---------------------");
        log.info(userVO.toString());
        log.info("---------------------");
        return "/user/login";
    }

    @PostMapping("/login")
    public String loginOK(UserVO userVO){
        log.info("---------------------");
        log.info(userVO.toString());
        log.info("---------------------");
        return "/index";
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


    @PostMapping("/serviceAddressOk")
    public String serviceAddressOk(UserVO userVO){
        log.info("-------------------------------------");
        log.info("userVO : " + userVO.toString());
        log.info("-------------------------------------");

        userService.modifyAddress(userVO);
        return "/service/serviceSubscribePayment";
    }

    @PostMapping("/servicePaymentOk")
    public String servicePaymentOk(UserVO userVO){
        log.info("-------------------------------------");
        log.info("userVO : " + userVO.toString());
        log.info("-------------------------------------");

        userService.changeService(userVO);
        return "/index";
    }

}