package com.project.washgogo.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderVO {
	private Long orderNumber;           // 주문번호(SEQUENCE, PK)
	private Long userNumber;            // 사용자번호(USER FK)
	private String orderDate;           // 주문일자(DEFAULT SYSDATE)
	private String orderProgress;       // 진행도(DEFAULT '세탁 중')
	private String orderAlert;          // 수거 시 알림 시간
	private String orderRequestMessage; // 세탁 요청 사항
	private Long orderTotalPrice;       // 총액
}
