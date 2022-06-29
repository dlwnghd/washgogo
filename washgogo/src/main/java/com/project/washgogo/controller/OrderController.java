package com.project.washgogo.controller;

import com.project.washgogo.domain.dao.UserDAO;
import com.project.washgogo.domain.vo.UserVO;
import com.project.washgogo.service.OrderService;
import com.project.washgogo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/*
 *  TASK        URL                     METHOD      PARAMETER       FORM        URL이동
 *
 *  신청 안내   /order/requestGuide     GET         userNumber      없음        /order/requestSelect
 *  수거 신청   /order/requestSelect    POST        모든 항목       필요        /index
 *  수거 취소   index에서 만약 신청한 게 있다면 취소로 바뀌게 변환
 *
 *
 *
 *
 *   전체목록    /board/list          GET         없음             없음
 *   등록       /board/register      POST         모든 항목        필요          /board/list
 *   조회       /board/read          GET          boardNumber     없음
 *   삭제       /board/remove        POST         boardNumber     없음          /board/list
 *   수정       /board/modify        POST         모든 항목        필요          /board/read
 *
 * */

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order/*")
public class OrderController {
	private final OrderService orderService;
	private final UserService userService;
	private final UserVO userVO;

	@GetMapping("requestGuide")
	public String requestGuide() {
		return "/order/requestGuide";
	}

	@GetMapping("requestSelect")
	public String requestSelect(HttpSession session, Model model) {
		long userNumber = (long)session.getAttribute("userNumber");
		UserVO user = userService.loadUserInfo(userNumber);
		model.addAttribute("userVO", user);
		return "/order/requestSelect";
	}

//	@PostMapping("requestSelect")
//	public String requestSelectOk(UserVO userVO, Model model) {
//		log.info("-------------------------------------");
//		log.info("userVO : " + userVO.toString());
//		log.info("-------------------------------------");
//		model.addAttribute("userVO", userService.loadUserInfo(1l));
//		return "/order/requestSelect";
//	}
}
