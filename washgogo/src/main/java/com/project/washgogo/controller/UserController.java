package com.project.washgogo.controller;

import com.project.washgogo.domain.vo.OrderVO;
import com.project.washgogo.domain.vo.UserVO;
import com.project.washgogo.domain.vo.*;
import com.project.washgogo.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

//SMS용으로 추가
import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderListService orderListService;
    private final NoticeService noticeService;
    private final ServiceAreaService serviceAreaService;
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

    @PostMapping("myModifyAddress")
    public String myModifyAddress(HttpSession session, UserVO userVO, @ModelAttribute("selectedServiceType") String selectedServiceType){
        log.info("-----------------postAddress--------------------");
        log.info("userVO : " + userVO.toString());
        log.info("-------------------------------------");

        return "/user/myModifyAddress";
    }

    @PostMapping("/myModifyingInformationOk")
    public String myModifyingInformationOk(HttpSession session, UserVO userVO, @ModelAttribute("selectedServiceType") String selectedServiceType, Model model){
        log.info("------------------------------------");
        log.info("userVO : " + userVO.toString());
        log.info("-------------------------------------");
        Long userNumber = Long.parseLong(String.valueOf(session.getAttribute("userNumber")));
        userVO.setUserNumber(userNumber);
        userService.modifyAddress(userVO);
        log.info("-------------주소 수정 완료!!!!!!!!!!!------------------------");
        long number = (long)session.getAttribute("userNumber");
        model.addAttribute("loadUserInfo", userService.loadUserInfo(number));
        return "/user/modifyingInformation";
    }

    @PostMapping("informationModify")
    @ResponseBody
    public String informationModify(@RequestBody UserVO userVO, HttpSession session){
        long number = (long)session.getAttribute("userNumber");
        log.info(String.valueOf(number));
        userVO.setUserNumber(number);
        log.info("불러온 후 vo 출력 : " + userVO.toString());
        //변경
        userService.modifyUserInfo(userVO);
        log.info(userVO.toString());
        session.removeAttribute("userName");
        session.setAttribute("userName", userVO.getUserName());
        return "/user/modifyingInformation";
    }

    @PostMapping("modifyProfile")
    public String modifyProfile(UserVO userVO, HttpSession session, Model model){
        log.info(userVO.toString());
        long userNumber = (long)session.getAttribute("userNumber");
        UserVO user = userService.loadUserInfo(userNumber);
        ProfileVO profileVO = userVO.getProfile();
        profileVO.setUserVO(user);
        user.setProfile(profileVO);
        if(userService.getProfile(userNumber) != null){

        }
        userService.modifyProfile(user);
        return myPage(user, session, model);
    }

    @DeleteMapping("removeProfile/{userNumber}")
    @ResponseBody
    public String removeProfile(@PathVariable("userNumber") Long userNumber){
        userService.removeProfile(userNumber);
        return "프로필 사진 DB삭제 성공";
    }

    @PostMapping("informationRemove")
    @ResponseBody
    public String informationRemove(UserVO userVO, HttpSession session){
        long number = (long)session.getAttribute("userNumber");
        userVO.setUserNumber(number);
        userService.resignMember(userVO.getUserNumber());
        session.removeAttribute("userNumber");
        session.removeAttribute("userName");
        return "/index";
    }

    @GetMapping("point")
    public String point(UserVO userVO, HttpSession session, Model model) {
        long number = (long)session.getAttribute("userNumber");
        model.addAttribute("showPoint", userService.showPoint(number));
        return "/user/point";
    }

    @GetMapping("useService")
    public String useService(HttpServletResponse response, HttpSession session, Model model) throws IOException{
        long number = (long)session.getAttribute("userNumber");

        UserVO userVO = userService.loadUserInfo(number);
        if(userVO.getUserServiceType() == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('먼저 서비스를 신청해주세요.'); location.href='/index';</script>");
            out.flush();
        }

        model.addAttribute("loadUserInfo", userService.loadUserInfo(number));
        return "/user/useService";
    }

    //    서비스 변경 페이지로 이동
    @GetMapping("serviceChange")
    public String serviceChange(UserVO userVO, HttpSession session, Model model){
        log.info("---------------------");
        log.info("---serviceChange/GET---");
        log.info("---------------------");
        long number = (long)session.getAttribute("userNumber");
        model.addAttribute("userVO", userService.loadUserInfo(number));
        log.info("userVO : "+userVO);
        log.info("model : "+model);
        log.info("GET/userService : "+userService.loadUserInfo(number).getUserServiceType());
        return "/user/serviceChange";
    }

//        서비스 변경 페이지에서 변경 버튼 클릭 시
    @PostMapping("serviceChange")
    public RedirectView serviceChangeOK(UserVO userVO, HttpSession session, Model model){
        log.info("---------------------");
        log.info("---serviceChangeOK/POST---");
        log.info("---------------------");
        long number = (long)session.getAttribute("userNumber");
        userVO = userService.loadUserInfo(number);
        model.addAttribute("userVO", userService.loadUserInfo(number));
        if(userVO.getUserServiceType().equals("Once")){
            userVO.setUserServiceType("Several");
            userVO.setUserLaunderetteType("Regular");
        }else if(userVO.getUserServiceType().equals("Several")){
            userVO.setUserServiceType("Once");
            userVO.setUserLaunderetteType("선택 안 함");
        }
        userService.changeService(userVO);
        return new RedirectView("/user/serviceChange");
    }

    @GetMapping("changeCancel")
    public String changeCancel(UserVO userVO){
        return "/user/changeCancel";
    }

    @PostMapping("serviceRemove")
    @ResponseBody
    public String serviceRemove(HttpSession session, UserVO userVO) {
        long number = (long)session.getAttribute("userNumber");
        userVO.setUserNumber(number);
        userService.removeService(number);
        return "/user/myPage";
    }

    @GetMapping("paymentDetails")
    public String paymentDetails(OrderVO order,UserVO uservo,HttpSession session, Model model) {
        long number = (long)session.getAttribute("userNumber");
        return "/user/paymentDetails";
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

//    ###로그인 / 회원가입###
//    회원가입 페이지로 이동
    @GetMapping("join")
    public String join(){
        log.info("---------------------");
        log.info("------join/Get-------");
        log.info("---------------------");
        return "/user/join";
    }

//    이메일 중복 확인
    @ResponseBody //REST
    @PostMapping("checkEmail")
    public boolean checkEmail(@RequestBody String userEmail){
        log.info("---------------------");
        log.info("---checkEmail/REST---");
        log.info("---------------------");
//        log.info("전달받은 email : "+ userEmail);
//        log.info("userService : "+userService.checkEmail(userEmail));
        return userService.checkEmail(userEmail);   // true : 이미 사용하고 있는 이메일
        //SELECT COUNT(USER_EMAIL) FROM TBL_USER WHERE USER_EMAIL = #{userEmail}
    }

    //    회원 존재 확인 및 인증번호 생성
    //    return을 boolean이 아닌 인증번호를 전달해 주어야 한다.
    @ResponseBody //REST
    @PostMapping("/checkUser")
    public String checkUser(@RequestBody UserVO userVO){
        log.info("-------------------------");
        log.info("---findIdPwPostMapping---");
        log.info("-------------------------");
//        log.info("전달받은 email : "+ userVO.getUserEmail());
//        log.info("전달받은 전화번호 : "+ userVO.getUserPhonenum());
//        log.info("userService : " + userService.checkUser(userVO));
        resultNum = "";     // 인증번호가 쌓이는 것을 방지하기 위해 인증번호 초기화

        // 반복문으로 인증번호를 생성
        for (int i=0; i<letter; i++) {
            createNum = random.nextInt(9);		//0부터 9까지 올 수 있는 1자리 난수 생성
            ranNum =  Integer.toString(createNum);  //1자리 난수를 String으로 형변환
            resultNum += ranNum;			//생성된 난수(문자열)을 원하는 수(letter)ㅇ만큼 더하며 나열
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
//        params.put("text", "[WashGoGo] 인증번호 ["+ resultNum +"]를 입력하세요.");   // 보내는 문자
//        params.put("app_version", "test app 1.2"); // application name and version
//
//        try {
//            JSONObject obj = (JSONObject) coolsms.send(params);
//            System.out.println(obj.toString());
//        } catch (CoolsmsException e) {
//            System.out.println(e.getMessage());
//            System.out.println(e.getCode());
//        }

        log.info("resultNum : " + resultNum);   // 인증번호 확인용 log.info
        return userService.checkUser(userVO) ? resultNum : null;   // userService.checkUser(userVO)이 true : 존재하는 유저
    }

//    인증번호 확인 성공
    @PostMapping("/checkVerifyNum")
    public RedirectView verifyOK(UserVO userVO, RedirectAttributes rttr){
        log.info("-------------------------");
        log.info("---checkVerifyNum/Post---");
        log.info("-------------------------");
//        log.info("userService : " + userService.findUserNumber(userVO));
//        log.info("찾는 유저의 번호 : " + userService.findUserNumber(userVO));
        rttr.addFlashAttribute("userNumber",userService.findUserNumber(userVO));
        return new RedirectView("/user/resetPw");
    }

//    회원가입
    @PostMapping("join")
    public String joinOK( UserVO userVO){
        log.info("---------------------");
        log.info("-----joinOK/Post-----");
        log.info("---------------------");

        userService.join(userVO);

        if(userVO == null){  // 사용자의 번호가 인식되지 않는다면
            log.info("---------------------");
            log.info("-----회원가입 실패-----");
            log.info("---------------------");
//            log.info("userVO : " + userVO);
            return "/user/join";
        }
        log.info("---------------------");
        log.info("-----회원가입 성공-----");
        log.info("---------------------");
//        log.info("userVO : " + userVO);
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

//    로그인 시 유저 존재 확인
    @ResponseBody //REST
    @PostMapping("/loginCheck")
    public boolean loginCheck(@RequestBody UserVO userVO){
        log.info("---------------------");
        log.info("--------loginCheck/REST--------");
        log.info("---------------------");
        log.info("userEmail : " + userVO.getUserEmail());
        log.info("userPw : " + userVO.getUserPw());
        log.info("userNumber : " + userVO.getUserNumber());
        log.info("userNumber 찾기 : " + userService.login(userVO.getUserEmail(), userVO.getUserPw()));
        if(userService.login(userVO.getUserEmail(), userVO.getUserPw()) == null){
            log.info("---------------------");
            log.info("-------유저 없음-------");
            log.info("---------------------");
            return false;
        }
        log.info("---------------------");
        log.info("---로그인 성공---");
        log.info("---------------------");
        return true;
    }

//    로그인
    @PostMapping("login")
    public RedirectView loginOK(UserVO userVO, HttpServletRequest request){
        log.info("---------------------");
        log.info("---------loginOK/POST---------");
        log.info("---------------------");
        log.info("loginCheck : "+loginCheck(userVO));
        if(userService.login(userVO.getUserEmail(), userVO.getUserPw()) == null){
            log.info("---------------------");
            log.info("---로그인 실패---");
            log.info("---------------------");
//            log.info("userEmail : " + userVO.getUserEmail());
//            log.info("userPw : " + userVO.getUserPw());
//            log.info("userNumber : " + userVO.getUserNumber());
//            null을 보낸다고 해도 redirect를 막을 수는 없음;;

            return null;
        }
        HttpSession session = request.getSession();
        Long userNumber = userService.login(userVO.getUserEmail(), userVO.getUserPw());
        UserVO user = userService.loadUserInfo(userNumber);
        String userName = user.getUserName();
        log.info("---------------------");
        log.info("---로그인 성공---");
        log.info("---------------------");
        session.setAttribute("userNumber", userNumber);
        session.setAttribute("userName", userName);
        if(userService.getProfile(userNumber) != null){
            ProfileVO profile = userService.getProfile(userNumber);
            profile.setUserVO(user);
            session.setAttribute("profile", profile);
        }

        return new RedirectView("/index");

    }

//    로그아웃
    @GetMapping("logout")
    public RedirectView logout(HttpServletRequest request){
        log.info("---------------------");
        log.info("--------logout/GET---------");
        log.info("---------------------");
        HttpSession session = request.getSession();
        log.info("logout에서 session : "+session.getAttribute("userNumber").toString());
        session.invalidate();
        return new RedirectView("/index");
    }

//    비밀번호 찾기 페이지로 이동
    @GetMapping("/findIdPw")
    public String findIdPw(){
        log.info("---------------------");
        log.info("---findIdPw/GET---");
        log.info("---------------------");
        log.info("resultNum : " + resultNum);
        return "/user/findIdPw";
    }


//    비밀번호 찾기
    @PostMapping("/findIdPw")
    public String findIdPwOK(UserVO userVO){
        log.info("---------------------");
        log.info("---findIdPw/POST---");
        log.info("---------------------");
//        log.info("유저번호 : " + userVO.getUserNumber());
        userVO.setUserPw(userVO.getUserPw());
//        log.info("유저의 비밀번호 : " + userVO.getUserPw());
        userService.changePw(userVO);
        return "/user/login";
    }

    @GetMapping("/resetPw")
    public String resetPw(){
        log.info("---------------------");
        log.info("---resetPw/GET---");
        log.info("---------------------");
        return "/user/resetPw";
    }

    @PostMapping("/resetPw")
    public String resetPwOK(){
        log.info("---------------------");
        log.info("---resetPwOK/POST---");
        log.info("---------------------");
        return "/user/resetPw";
    }

    // 이용 내역
    @GetMapping("used")
    public String used(HttpSession session, Model model, HttpServletResponse response) throws IOException {
        long userNumber = (long)session.getAttribute("userNumber");
        UserVO user = userService.loadUserInfo(userNumber);
        OrderVO order = orderService.getRecent(userNumber);

        if(order == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('사용한 내역이 없습니다.'); location.href='/index';</script>");
            out.flush();
        }

        List<OrderListVO> orderList = orderListService.getRecentList(order.getOrderNumber());

        model.addAttribute("order", order);
        model.addAttribute("orderList", orderList);
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
        model.addAttribute("selectedServiceType", "Several");
        return "/service/serviceDetail";
    }

    @GetMapping("serviceOnceDetail")
    public String serviceOnceDetail(HttpSession session, Model model){
        if(session.getAttribute("userNumber")  == null){
            return "/service/serviceOnceDetail";
        }
        Long userNumber = Long.parseLong(String.valueOf(session.getAttribute("userNumber")));
        UserVO user = userService.loadUserInfo(userNumber);
        model.addAttribute("userServiceType", user.getUserServiceType());
        model.addAttribute("selectedServiceType", "Once");
        return "/service/serviceOnceDetail";
    }

    @GetMapping("serviceSubscribeAddress")
    public String serviceSubscribeAddress(HttpSession session, @ModelAttribute("selectedServiceType") String selectedServiceType, RedirectAttributes rttr){
        if(session.getAttribute("userNumber")  == null){
            return "/user/login";
        }

        Long userNumber = Long.parseLong(String.valueOf(session.getAttribute("userNumber")));
        UserVO user = userService.loadUserInfo(userNumber);
        if(user.getUserAddress() != null) {
            rttr.addAttribute("selectedServiceType", selectedServiceType);
            return "redirect:/user/servicePayment";
        }

        rttr.addAttribute("selectedServiceType", selectedServiceType);
        return "service/serviceSubscribeAddress";
    }

    @PostMapping("modifyAddress")
    public String modifyAddress(HttpSession session, UserVO userVO, @ModelAttribute("selectedServiceType") String selectedServiceType){
        log.info("-----------------postAddress--------------------");
        log.info("userVO : " + userVO.toString());
        log.info("-------------------------------------");

        return "/service/modifyAddress";
    }

    @PostMapping("/checkServiceArea")
    @ResponseBody
    public boolean checkServiceArea(@RequestBody String address){
        return serviceAreaService.isServiceArea(address);
    }

    @PostMapping("/serviceAddressOk")
    public String serviceAddressOk(HttpSession session, UserVO userVO, @ModelAttribute("selectedServiceType") String selectedServiceType, Model model){
        log.info("-------------------------------------");
        log.info("userVO : " + userVO.toString());
        log.info("-------------------------------------");
        Long userNumber = Long.parseLong(String.valueOf(session.getAttribute("userNumber")));
        userVO.setUserNumber(userNumber);
        userService.modifyAddress(userVO);
        UserVO user = userService.loadUserInfo(userNumber);
        model.addAttribute("selectedServiceType", selectedServiceType);
        model.addAttribute("userServiceType", user.getUserServiceType());
        return "/service/serviceSubscribePayment";
    }

    // serviceSubscribeAddress 건너뛰고 바로 serviceSubscribePayment로 이동했을 때
    @GetMapping("/servicePayment")
    public String servicePayment(HttpSession session, @ModelAttribute("selectedServiceType") String selectedServiceType, Model model){
        Long userNumber = Long.parseLong(String.valueOf(session.getAttribute("userNumber")));
        UserVO user = userService.loadUserInfo(userNumber);
        model.addAttribute("userVO", user);
        model.addAttribute("selectedServiceType",selectedServiceType);
        model.addAttribute("userServiceType", user.getUserServiceType());
        return "/service/serviceSubscribePayment";
    }

    @PatchMapping("/servicePaymentOk")
    @ResponseBody
    public boolean servicePaymentOk(@RequestBody UserVO userVO, HttpSession session){
        log.info("-------------------------------------");
        log.info("userVO : " + userVO.toString());
        log.info("-------------------------------------");
        userVO.setUserNumber(Long.parseLong(String.valueOf(session.getAttribute("userNumber"))));
        boolean isSuccess = userService.changeService(userVO);
        return isSuccess;
    }

    @GetMapping("/serviceChangeComplete")
    public String serviceChangeComplete(){
        return "/service/serviceChangeComplete";
    }


    @GetMapping("myModifyAddress")
    public String myModifyAddress(HttpSession session, UserVO userVO){
        log.info("-----------------postAddress--------------------");
        log.info("userVO : " + userVO.toString());
        log.info("-------------------------------------");

        return "/user/myModifyAddress";
    }

    @PostMapping("/serviceAddressMyPage")
    public String serviceAddressMyPage(HttpSession session, UserVO userVO, Model model){
        Long userNumber = Long.parseLong(String.valueOf(session.getAttribute("userNumber")));
        userVO.setUserNumber(userNumber);
        userService.modifyAddress(userVO);
        UserVO user = userService.loadUserInfo(userNumber);
        return "/user/modifyingInformation";
    }
}