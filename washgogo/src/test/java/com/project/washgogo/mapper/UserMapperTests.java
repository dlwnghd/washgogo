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

//    회원가입 테스트
//    @Test
//    public void insertTest(){
//        UserVO userVO = new UserVO();
//        userVO.setUserEmail("abcdefg@naver.com");
//        userVO.setUserPw("123456");
//        userVO.setUserName("이주홍1");
//        userVO.setUserPhonenum("01099999999");
//        userMapper.insert(userVO);
//    }

//    로그인 테스트
//    @Test
//    public void loginTest(){
//        log.info(userMapper.login("alion0513@naver.com","1234").toString());
//    }

}















