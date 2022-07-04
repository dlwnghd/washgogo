package com.project.washgogo.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderListVO {
	private Long orderNumber;       // 주문번호(ORDER FK)
	private Long laundryNumber;     // 세탁물번호(LAUNDRY FK)
	private String orderType;       // 개별클리닝 혹은 프리미엄 선택
	private String orderPmOption;   // 프리미엄 옵션
	private String orderPmMessage;  // 프리미엄 요청사항
	private Long orderEAQuantity;   // 해당 세탁물 수량

//	Laundry테이블 join을 위해서 기입
	private String laundryName;     // 세탁물명
	private Long laundryPrice;      // 기본 혹은 개별클리닝 가격
	private Long laundryPmPrice;    // 프리미엄 가격
	private String laundryURL;      // 사진명.확장자명
}
