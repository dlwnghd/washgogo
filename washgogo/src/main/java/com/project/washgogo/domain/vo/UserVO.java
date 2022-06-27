package com.project.washgogo.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserVO {
	private Long userNumber;
	private String userName;
	private String userPw;
	private String userEmail;
	private String userPhonenum;
	private String userAddress;
	private String userAddressDetail;
	private String userEntranceType;
	private String userEntrancePw;
	private String userEntranceMessage;
	private String userServiceType;
	private String userLaunderetteType;
	private String userCardnum;
	private String userPoint;
}
