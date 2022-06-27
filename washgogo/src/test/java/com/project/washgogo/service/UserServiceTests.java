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
