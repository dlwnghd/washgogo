package com.project.washgogo.UserDAOTests;

import com.project.washgogo.domain.dao.UserDAO;
import com.project.washgogo.domain.vo.UserVO;
import com.project.washgogo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserDAOTests {
    @Autowired
    private UserDAO userDAO;


//    @Test
//    public void getListTest(){
//        userDAO.getList().stream().map(UserVO::toString).forEach(log::info);
//    }

    // 테스트 완료
//    @Test
//    public void selectUserInfoTest(){
//        log.info(userDAO.loadUserInfo(1L).toString());
//    }

    // 테스트 완료
//    @Test
//    public void updateAddressTest() {
//        UserVO userVO = userDAO.loadUserInfo(1L);
//        userVO.setUserAddress("서초구 강남대로 5길");
//        userVO.setUserAddressDetail("명성빌딩 1006호");
//        userVO.setUserEntranceType("공동현관 비밀번호");
//        userVO.setUserEntrancePw("12345");
//
//        log.info("UPDATE : " + userDAO.modifyAddress(userVO));
//    }

    // 테스트 완료
//    @Test
//    public void updateServiceTest(){
//    UserVO userVO = userDAO.loadUserInfo(1L);
//    userVO.setUserServiceType("여러 번 이용");
//    userVO.setUserLaunderetteType("Regular");
//
//    log.info("SERVICE UPDATE : " + userDAO.changeService(userVO));
//    }
}















