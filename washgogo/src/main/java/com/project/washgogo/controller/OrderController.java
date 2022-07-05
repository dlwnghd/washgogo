package com.project.washgogo.controller;

import com.project.washgogo.domain.vo.OrderListVO;
import com.project.washgogo.domain.vo.OrderVO;
import com.project.washgogo.domain.vo.UserVO;
import com.project.washgogo.service.OrderListService;
import com.project.washgogo.service.OrderService;
import com.project.washgogo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order/*")
public class OrderController {
	private final UserService userService;
	private final OrderService orderService;
	private final OrderListService orderListService;

	@GetMapping("requestGuide")
	public String requestGuide(HttpSession session, HttpServletResponse response) throws IOException {
		if(session.getAttribute("userNumber")  == null) { return "/user/login"; }

		long userNumber = (long)session.getAttribute("userNumber");
		UserVO user = userService.loadUserInfo(userNumber);
		OrderVO order = orderService.getRecent(userNumber);
		List<OrderListVO> orderList = orderListService.getRecentList(order.getOrderNumber());

		if(user.getUserServiceType() == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('먼저 서비스를 신청해주세요.'); location.href='/index';</script>");
			out.flush();
		}

		if(order.getOrderNumber() == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('사용한 내역이 없습니다.'); location.href='/index';</script>");
			out.flush();
		}

		return "/order/requestGuide";
	}

	@GetMapping("/requestSelect")
	public String requestSelect(HttpSession session, Model model) {
		long userNumber = (long)session.getAttribute("userNumber");
		UserVO user = userService.loadUserInfo(userNumber);

		model.addAttribute("userVO", user);

		return "/order/requestSelect";
	}

	@PostMapping("/requestSelectOk")
	public String requestSelectOk(OrderVO orderVO, HttpSession session) {
		long userNumber = (long)session.getAttribute("userNumber");

		orderVO.setUserNumber(userNumber);
		log.info("-------------------------------------");
		log.info("orderVO : " + orderVO.toString());
		log.info("-------------------------------------");
		orderService.applyRequest(orderVO);

		orderListService.insertShipping(orderVO.getOrderNumber());

		return ("/index");
	}
	//공지 추가 링크
	@GetMapping("payment")
	public void payment(){
	};
}
