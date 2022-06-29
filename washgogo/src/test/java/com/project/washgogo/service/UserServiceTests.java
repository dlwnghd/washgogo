package com.project.washgogo.service;

import com.project.washgogo.domain.dao.UserDAO;
import com.project.washgogo.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserServiceTests {
    @Autowired
    public UserService userService;

//    @Test
//    public void join() {
//        UserVO userVO = new UserVO();
//        userVO.setUserEmail("uuuu@naver.com");
//        userVO.setUserPw("123456");
//        userVO.setUserName("이주홍2");
//        userVO.setUserPhonenum("01099999999");
//        userService.join(userVO);
//    }

//    이메일 중복 테스트
//    @Test
//    public void checkEmail(){
//        userService.checkEmail("alion0513@naver.com");
//    }

//    @Test
//    public void login(){
//        userService.login("alion0513@naver.com","1234");
//    }

    // 테스트 완료
//    @Test
//    public void selectUserInfoTest(){
//        log.info(userService.loadUserInfo(1L).toString());
//    }

    // 테스트 완료
//    @Test
//    public void updateAddressTest() {
//        UserVO userVO = userService.loadUserInfo(1L);
//        userVO.setUserAddress("서초구 강남대로 4길");
//        userVO.setUserAddressDetail("명성빌딩 1004호");
//        userVO.setUserEntranceType("공동현관 비밀번호");
//        userVO.setUserEntrancePw("1234");
//
//        log.info("UPDATE : " + userService.modifyAddress(userVO));
//    }

    // 테스트 완료
//    @Test
//    public void updateServiceTest(){
//    UserVO userVO = userService.loadUserInfo(1L);
//    userVO.setUserServiceType("여러 번 이용");
//    userVO.setUserLaunderetteType("Regular");
//
//    log.info("SERVICE UPDATE : " + userService.changeService(userVO));
//    }
}
