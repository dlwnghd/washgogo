package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.OrderVO;
import com.project.washgogo.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.core.annotation.Order;

import java.util.List;

@Mapper
public interface UserMapper {
//    유저 정보 목록
    public List<UserVO> getList();
//    회원가입
    public void insert(UserVO userVO);
//    로그인
    public Long login(String userEmail, String userPw);
//    마이페이지
    public UserVO selectUserInfo(Long userNumber);
//    회원 정보 수정
    public int updateUserInfo(UserVO userVO);
//    서비스 변경
    public int updateService(UserVO userVO);
//    주소 입력 및 수정
    public int updateAddress(UserVO userVO);
//    회원 탈퇴
    public int delete(Long userNumber);


//    이주홍 연습용
//    현재시간
    public String getTime();
}
