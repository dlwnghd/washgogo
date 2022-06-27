package com.project.washgogo.controller;

import com.project.washgogo.domain.dao.UserDAO;
import com.project.washgogo.domain.vo.OrderVO;
import com.project.washgogo.domain.vo.UserVO;
import com.project.washgogo.service.UserService;
import com.project.washgogo.domain.vo.*;
import com.project.washgogo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

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
    //공지 추가 링크
    @GetMapping("noticeAdd")
    public void noticeAdd(){
    };
    //공지사항
    @GetMapping("notice")
    public String getList(Criteria criteria, Model model){
        //log.info("----------------------------");
        //log.info("list............. : " + criteria);
        //log.info("----------------------------");

        model.addAttribute("noticeList", noticeService.getList(criteria));
        model.addAttribute("pageDTO", new PageDTO(criteria, noticeService.getTotal(criteria)));
        return "/user/notice";
    }
    //신규 공지사항 추가
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
    //공지사항 내용 조회 및 해당 noticeNumber 수정 폼 이동(조회 이유는 수정 폼 페이지 띄우기 위함)
    @GetMapping({"noticeEdit", "modify"})
    public void read(Long noticeNumber, HttpServletRequest req, Model model){
    //    log.info("----------------------------");
    //    log.info(req.getRequestURI() + "............. : " + boardNumber);
    //    log.info("----------------------------");
        model.addAttribute("notice", noticeService.get(noticeNumber));
    }
    //수정
    @PostMapping("modify")
    public RedirectView modify(NoticeVO noticeVO, RedirectAttributes rttr){
    //    log.info("----------------------------");
    //    log.info("modify............. : " + noticeVO);
    //    log.info("----------------------------");
          noticeService.modify(noticeVO);
//        컨트롤러에서 다른 컨트롤러의 매개변수로 파라미터를 전달할 때에는
//        addAttribute(), 쿼리스트링 방식으로 전달해야 받을 수 있다.
//        Flash방식은 최종 응답 화면에서 사용될 파라미터를 전달할 때에만 사용하도록 한다.
          rttr.addAttribute("noticeNumber", noticeVO.getNoticeNumber());
          return new RedirectView("/user/notice");
    }
    //    삭제
    @PostMapping("remove")
    public String remove(Long noticeNumber, Criteria criteria, Model model){
        //log.info("----------------------------");
        //log.info("remove............. : " + noticeNumber);
        //log.info("----------------------------");

        noticeService.remove(noticeNumber);
        return getList(criteria, model);
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

    // 자유이용서비스
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