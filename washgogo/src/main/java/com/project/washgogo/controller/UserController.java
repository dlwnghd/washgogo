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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

//SMS용으로 추가
import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import java.util.Random;


@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final NoticeService noticeService;
    Random random = new Random();		//랜덤 함수 선언
    int createNum = 0;  			//1자리 난수
    String ranNum = ""; 			//1자리 난수 형변환 변수
    int letter    = 6;			//난수 자릿수:6
    String resultNum = "";      //결과 난수


    @GetMapping("myPage")
    public String myPage(UserVO userVO, HttpSession session, Model model){
        long number = (long)session.getAttribute("userNumber");
        model.addAttribute("myPageInfo", userService.myPageInfo(number));
        return "/user/myPage";
    }

    @GetMapping("modifyingInformation")
    public String modifyingInformation(UserVO userVO, HttpSession session, Model model){
        long number = (long)session.getAttribute("userNumber");
        model.addAttribute("loadUserInfo", userService.loadUserInfo(number));
        return "/user/modifyingInformation";
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
        return "/user/point";
    }



    @PostMapping("modifyingInformation")
    public RedirectView modifyingInformationModify(UserVO userVO, RedirectAttributes rttr){
        log.info("----------------------------");
        log.info(userVO.toString());
        log.info("----------------------------");

        userService.modifyUserInfo(userVO);
        rttr.addFlashAttribute("userNumber", userVO.getUserNumber());
        return new RedirectView("/user/modifyingInformation");
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

//    이메일 중복 확인
    @ResponseBody //REST
    @PostMapping("checkEmail")
    public boolean checkEmail(@RequestBody String userEmail){
        log.info("전달받은 email : "+ userEmail);
        log.info("userService : "+userService.checkEmail(userEmail));
        return userService.checkEmail(userEmail);   // true : 이미 사용하고 있는 이메일
        //SELECT COUNT(USER_EMAIL) FROM TBL_USER WHERE USER_EMAIL = #{userEmail}
    }

//    인증번호 확인
    @ResponseBody //REST
    @PostMapping("VerifyNumber")
    public boolean VerifyNumber(@RequestBody String VerifyNumber){
        log.info("전달받은 인증번호 : "+ VerifyNumber);
        log.info(VerifyNumber.equals(resultNum)+"");
        return VerifyNumber.equals(resultNum);   // true : 이미 사용하고 있는 이메일
    }

    //    회원 존재 확인
    @ResponseBody //REST
    @PostMapping("/checkUser")
    public boolean checkUser(@RequestBody UserVO userVO){
        log.info("---------------------");
        log.info("---findIdPwPostMapping---");
        log.info("---------------------");
        log.info("전달받은 email : "+ userVO.getUserEmail());
        log.info("전달받은 전화번호 : "+ userVO.getUserPhonenum());
        log.info("userService : " + userService.checkUser(userVO));

        for (int i=0; i<letter; i++) {

            createNum = random.nextInt(9);		//0부터 9까지 올 수 있는 1자리 난수 생성
            ranNum =  Integer.toString(createNum);  //1자리 난수를 String으로 형변환
            resultNum += ranNum;			//생성된 난수(문자열)을 원하는 수(letter)만큼 더하며 나열
        }

////        휴대폰에 인증번호 전송
//        String api_key = "NCSHUXHNNINOL8AT";
//        String api_secret = "IMDMEXAZSWQWO7OR993KMVDEXCOK0ZDV";
//        Message coolsms = new Message(api_key, api_secret);
//
//        // 4 params(to, from, type, text) are mandatory. must be filled
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("to", userVO.getUserPhonenum());    // 인증번호 받는 사람
//        params.put("from", "01088580291");  // 인증번호 보내는 사람
//        params.put("type", "SMS");  // 문자형태
//        params.put("text", "[WashGoGo] 인증번호 ["+ resultNum +"]를 입력하세요. 이주홍52");   // 보내는 문자
//        params.put("app_version", "test app 1.2"); // application name and version
//
//        try {
//            JSONObject obj = (JSONObject) coolsms.send(params);
//            System.out.println(obj.toString());
//        } catch (CoolsmsException e) {
//            System.out.println(e.getMessage());
//            System.out.println(e.getCode());
//        }

        log.info("resultNum : " + resultNum);
        return userService.checkUser(userVO);   // true : 존재하는 있는 유저
    }

//    회원가입
    @PostMapping("join")
    public String joinOK( UserVO userVO){
        log.info("--------joinOK/Post---------");
        log.info(userVO.toString());
        log.info("---------------------");

        userService.join(userVO);

        if(userVO == null){  // 사용자의 번호가 인식되지 않는다면
            log.info("---회원가입 실패---");
            log.info("userVO : " + userVO);
            return "/user/join";
        }

        log.info("---회원가입 성공---");
        log.info("userVO : " + userVO);
        log.info(userVO.toString());
        return "/user/login";
    }


//    로그인 페이지로 이동
    @GetMapping("login")
    public String login(){
        log.info("---------------------");
        log.info("---loginGetMapping---");
        log.info("---------------------");
        return "/user/login";
    }

    @PostMapping("login")
    public RedirectView loginOK(UserVO userVO, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long userNumber = userService.login(userVO.getUserEmail(), userVO.getUserPw());
        String userName = userService.loadUserInfo(userNumber).getUserName();

        //        존재하지 않는 user면? => ⭐강사님께 물어보기
        if(userService.login(userVO.getUserEmail(), userVO.getUserPw()) == null){
            log.info("---로그인 실패---");
            return new RedirectView("/user/login");
        }
        log.info("---로그인 성공---");
        session.setAttribute("userNumber", userNumber);
        session.setAttribute("userName", userName);
        return new RedirectView("/index");
    }

//    로그아웃
    @GetMapping("logout")
    public RedirectView logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info("logout에서 session : "+session.getAttribute("userNumber").toString());
        session.removeAttribute("userNumber");
        return new RedirectView("/index");
    }

//    비밀번호 찾기 페이지로 이동
    @GetMapping("/findIdPw")
    public String findIdPw(){
        log.info("---------------------");
        log.info("---findIdPwGetMapping---");
        log.info("---------------------");
        log.info("resultNum : " + resultNum);
        return "/user/findIdPw";
    }


//    비밀번호 찾기
    @PostMapping("/findIdPw")
    public String findIdPwOK(){
        log.info("---------------------");
        log.info("---findIdPwPostMapping---");
        log.info("---------------------");
        log.info("resultNum : " + resultNum);
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

    // 이용 내역
    @GetMapping("used")
    public String used(HttpSession session, Model model){
        //        long userNumber = (long)session.getAttribute("userNumber");
        //        model.addAttribute("order", orderService.getRecent(userNumber));

        //        model.addAttribute("orderList", orderListService.getRecentList(order.getOrderNumber()));
        return "/user/used";
    }

    // 자유이용서비스
    @GetMapping("serviceDetail")
    public String serviceDetail(HttpSession session, Model model){
        if(session.getAttribute("userNumber")  == null){
            return "/service/serviceDetail";
        }
        Long userNumber = Long.parseLong(String.valueOf(session.getAttribute("userNumber")));
        UserVO user = userService.loadUserInfo(userNumber);
        model.addAttribute("userServiceType", user.getUserServiceType());
        return "/service/serviceDetail";
    }

    @GetMapping("serviceSubscribeAddress")
    public String serviceSubscribeAddress(HttpSession session){
        if(session.getAttribute("userNumber")  == null){
            return "/user/login";
        }

        Long userNumber = Long.parseLong(String.valueOf(session.getAttribute("userNumber")));
        UserVO user = userService.loadUserInfo(userNumber);
        if(user.getUserAddress() != null) {
            return "redirect:/user/servicePayment";
        }

        return "service/serviceSubscribeAddress";
    }

    @PostMapping("modifyAddress")
    public String modifyAddress(UserVO userVO){
        log.info("-----------------postAddress--------------------");
        log.info("userVO : " + userVO.toString());

        return "/service/modifyAddress";
    }

    @PostMapping("/serviceAddressOk")
    public String serviceAddressOk(UserVO userVO, HttpSession session){
        log.info("-------------------------------------");
        log.info("userVO : " + userVO.toString());
        log.info("-------------------------------------");
        Long userNumber = Long.parseLong(String.valueOf(session.getAttribute("userNumber")));
        userVO.setUserNumber(userNumber);
        userService.modifyAddress(userVO);
        log.info("-------------------------------------");
        log.info("userVO : " + userVO.toString());
        log.info("-------------------------------------");
        return "/service/serviceSubscribePayment";
    }

    @GetMapping("/servicePayment")
    public String servicePayment(HttpSession session, Model model){
        Long userNumber = Long.parseLong(String.valueOf(session.getAttribute("userNumber")));
        UserVO user = userService.loadUserInfo(userNumber);
        model.addAttribute("userVO", user);
        return "/service/serviceSubscribePayment";
    }

    @GetMapping("serviceSubscribePayment")
    public String serviceSubscribePayment(){
        return "/service/serviceSubscribePayment";
    }

    @PostMapping("/servicePaymentOk")
    public RedirectView servicePaymentOk(UserVO userVO, HttpSession session){
        log.info("-------------------------------------");
        log.info("userVO : " + userVO.toString());
        log.info("-------------------------------------");
        userVO.setUserNumber(Long.parseLong(String.valueOf(session.getAttribute("userNumber"))));
        userService.changeService(userVO);
        return new RedirectView("/order/requestGuide");
    }
}