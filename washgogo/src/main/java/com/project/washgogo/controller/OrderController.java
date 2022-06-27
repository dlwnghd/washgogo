package com.project.washgogo.controller;

import com.project.washgogo.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  TASK        URL                     METHOD      PARAMETER       FORM        URL이동
 *
 *  신청 안내   /order/requestGuide     GET         userNumber      없음        /order/requestSelect
 *  수거 신청   /order/requestSelect    POST        모든 항목       필요        /index
 *  수거 취소
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
	private final OrderServiceImpl orderService;

	@GetMapping("requestGuide")
	public void requestGuide() {}

	@GetMapping("requestSelect")
	public void requestSelect() {

	}
}
