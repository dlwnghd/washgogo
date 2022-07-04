package com.project.washgogo.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderVO {
	private Long orderNumber;
	private Long userNumber;
	private String orderDate;
	private String orderProgress;
	private String orderAlert;
	private String orderRequestMessage;
	private Long orderTotalPrice;
}
