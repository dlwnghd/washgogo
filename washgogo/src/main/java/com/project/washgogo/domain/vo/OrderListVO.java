package com.project.washgogo.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderListVO {
	private Long orderNumber;
	private Long laundryNumber;
	private String orderPmChoose;
	private String orderPmOption;
	private String orderPmMessage;
	private String orderType;
	private Long orderEAQuantity;
	private String laundryName;
	private Long laundryPrice;
	private Long laundryPmPrice;
	private String laundryURL;
}
