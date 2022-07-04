package com.project.washgogo.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LaundryVO {
	private Long laundryNumber;     // 1 ~ 71 세탁물 번호(SEQUENCE, PK)
	private String laundryName;     // 세탁물명
	private Long laundryPrice;      // 기본 혹은 개별클리닝 가격
	private Long laundryPmPrice;    // 프리미엄 가격 
	private String laundryURL;      // 사진명.확장자명
}
