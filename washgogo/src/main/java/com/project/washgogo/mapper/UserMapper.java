package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
//    회원가입
    public void insert(UserVO userVO);
//    마이페이지
    public UserVO selectUserInfo(Long userNumber);
//    회원 정보 수정
    public void updateUserInfo(UserVO userVO);
//    서비스 변경
    public void updateService(UserVO userVO);
//    주소 입력 및 수정
    public void updateAddress(UserVO userVO);
//    회원 탈퇴
    public void delete(Long userNumber);
}
