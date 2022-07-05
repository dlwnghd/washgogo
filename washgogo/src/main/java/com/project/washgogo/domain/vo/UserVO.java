package com.project.washgogo.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class UserVO {
	private Long userNumber;            // 사용자 번호(SEQUENCE, PK)
	private String userEmail;           // 이메일(UNIQUE)
	private String userPw;              // 비밀번호
	private String userName;            // 사용자 이름
	private String userPhonenum;        // 전화번호
	private String userAddress;         // 주소
	private String userAddressDetail;   // 상세주소
	private String userEntranceType;    // 출입문 종류
	private String userEntrancePw;      // 공동현관 비밀번호
	private String userEntranceMessage; // 공동현관 메시지
	private String userServiceType;     // 서비스 타입
	private String userLaunderetteType; // 런드렛 타입
	private String userPoint;           // 포인트

	private List<ProfileVO> profile;
}
