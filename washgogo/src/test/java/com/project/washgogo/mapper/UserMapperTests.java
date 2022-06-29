package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;


//    @Test
//    public void getListTest(){
//        userMapper.getList().stream().map(UserVO::toString).forEach(log::info);
//    }

//    @Test
//    public void getTimeTest(){
//        log.info("----------------------------");
//        log.info(userMapper.getTime());
//        log.info("----------------------------");
//    }

//    회원가입 테스트
//    @Test
//    public void insertTest(){
//        UserVO userVO = new UserVO();
//        userVO.setUserEmail("tttt@naver.com");
//        userVO.setUserPw("123456");
//        userVO.setUserName("이주홍2");
//        userVO.setUserPhonenum("01099999999");
//        userMapper.insert(userVO);
//    }

//    이메일 중복 테스트
//    @Test
//    public void checkEmailTest(){
//        log.info(""+userMapper.checkEmail("alion0513@naver.com"));
//    }

    //    로그인 테스트
//    @Test
//    public void loginTest(){
//        log.info(""+userMapper.login("alion0513@naver.com","1234"));
//    }

    // 테스트 완료
//    @Test
//    public void selectUserInfoTest(){
//        log.info(userMapper.selectUserInfo(1L).toString());
//    }

    // 테스트 완료
//    @Test
//    public void updateAddressTest(){
//        UserVO userVO = userMapper.selectUserInfo(1L);
//        userVO.setUserAddress("서초구 강남대로 6길");
//        userVO.setUserAddressDetail("명성빌딩 1004호");
//        userVO.setUserEntranceType("공동현관 비밀번호");
//        userVO.setUserEntrancePw("1234");
//
//        log.info("ADDRESS UPDATE COUNT : " + userMapper.updateAddress(userVO));
//    }

    // 테스트 완료
//    @Test
//    public void updateServiceTest(){
//        UserVO userVO = userMapper.selectUserInfo(1L);
//        userVO.setUserServiceType("여러 번 이용");
//        userVO.setUserLaunderetteType("Regular");
//
//        log.info("SERVICE UPDATE COUNT : " + userMapper.updateService(userVO));
//    }


}















