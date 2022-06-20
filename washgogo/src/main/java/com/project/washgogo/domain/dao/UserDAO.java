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

    //    게시글 목록
    public List<UserVO> getList() { return userMapper.getList(); };
    //    회원가입
    public void insert(UserVO userVO) { userMapper.insert(userVO); }
    //    마이페이지
    public UserVO selectUserInfo(Long userNumber) { return userMapper.selectUserInfo(userNumber); }
    //    회원 정보 수정
    public void updateUserInfo(UserVO userVO) { userMapper.updateUserInfo(userVO); }
    //    서비스 변경
    public void updateService(UserVO userVO) { userMapper.updateService(userVO); }
    //    주소 입력 및 수정
    public void updateAddress(UserVO userVO) { userMapper.updateAddress(userVO); }
    //    회원 탈퇴
    public void delete(Long userNumber) { userMapper.delete(userNumber);}

}
