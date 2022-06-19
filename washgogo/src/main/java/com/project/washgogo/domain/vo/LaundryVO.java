package com.project.washgogo.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LaundryVO {
	private Long laundryNumber;
	private String laundryName;
	private Long laundryPrice;
	private Long laundryPmPrice;
}
