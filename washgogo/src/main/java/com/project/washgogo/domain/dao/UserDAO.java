package com.project.washgogo.domain.dao;

import com.project.washgogo.domain.vo.UserVO;
import com.project.washgogo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDAO {
    private final UserMapper userMapper;

    //    유저 정보 목록
    public List<UserVO> getList() { return userMapper.getList(); };
    //    회원가입
    public void join(UserVO userVO) {userMapper.insert(userVO); }
    //    로그인
    public Long selectUser(String userEmail, String userPw){return userMapper.login(userEmail, userPw);}
    //    마이페이지 내용 불러오기
    public UserVO loadUserInfo(Long userNumber) {
        return userMapper.selectUserInfo(userNumber);
    }
    //    회원 정보 수정
    public boolean modifyUserInfo(UserVO userVO) { return userMapper.updateUserInfo(userVO) == 1; }
    //    서비스 변경
    public boolean changeService(UserVO userVO) {
        return userMapper.updateService(userVO) == 1;
    }
    //    주소 입력 및 수정
    public boolean modifyAddress(UserVO userVO) {
        return userMapper.updateAddress(userVO) == 1;
    }
    //    회원 탈퇴
    public boolean resignMember(Long userNumber) {
        return userMapper.delete(userNumber) == 1;
    }
}
