package com.project.washgogo.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderVO {
	private String orderNumber;
	private Long userNumber;
	private String orderString;
	private String orderProgress;
	private String orderAlert;
	private String orderPmChoose;
	private String orderPmOption;
	private String orderPmMessage;
	private String orderTypeGeneral;
	private String orderTypeCleaning;
	private String orderRequestOption;
	private String orderRequestMessage;
	private Long laundryNumber;
	private Long orderEaQuantity;
	private Long orderTotalQuantity;
	private Long orderTotalPrice;
}
