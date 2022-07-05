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

import javax.servlet.http.HttpServletRequest;
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

		if(user.getUserServiceType() == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('먼저 서비스를 신청해주세요.'); location.href='/index';</script>");
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
	public String requestSelectOk(OrderVO orderVO, HttpSession session, HttpServletRequest request) {
		long userNumber = (long)session.getAttribute("userNumber");

		log.info("============================" + request.getParameter("washerNumber"));
		log.info("============================" + request.getParameter("cleaningNumber"));

		orderVO.setUserNumber(userNumber);
		log.info("-------------------------------------");
		log.info("orderVO : " + orderVO.toString());
		log.info("-------------------------------------");
		orderService.applyRequest(orderVO);

		if (!request.getParameter("washerNumber").isEmpty()) {
			Double number = Double.parseDouble(request.getParameter("washerNumber"));
			Double temp = 0.0;
			if (number > 3.0) {
				log.info("=============== 3.0보다 큼");
				temp = (number - 3) / 0.5;
				if ((number - 3) % 0.5 > 0){
					log.info("=============== 나머지가 0.5보다 작음");
					temp= temp + 1.0;
					log.info(String.valueOf(temp));
					orderListService.insertWasher2(orderVO.getOrderNumber(), Double.valueOf(temp).longValue());
				}
			}
			orderListService.insertWasher1(orderVO.getOrderNumber());
		}

		if (!request.getParameter("cleaningNumber").isEmpty()) {
			Long cleaningNumber = Long.parseLong(request.getParameter("cleaningNumber"));
			log.info("======================" + cleaningNumber);
			orderListService.insertCleaning(orderVO.getOrderNumber(), cleaningNumber);
		}

		orderListService.insertShipping(orderVO.getOrderNumber());
		orderService.setTotalPrice(orderVO.getOrderNumber());

		return ("/index");
	}

	//공지 추가 링크
	@GetMapping("payment")
	public void payment(){
	}
}
