package com.project.washgogo.service;

import com.project.washgogo.domain.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserVO> getList();
    public void join(UserVO userVO);
    public Long login(String userEmail, String userPw);
    public UserVO myPageInfo(Long userNumber);
    public UserVO loadUserInfo(Long userNumber);
    public boolean modifyUserInfo(UserVO userVO);
    public boolean changeService(UserVO userVO);
    public boolean modifyAddress(UserVO userVO);
    public boolean resignMember(Long userNumber);
    public boolean checkEmail(String userEmail);
    public boolean checkUser(UserVO userVO);
    public int findUserNumber(UserVO userVO);
    public void changePw(UserVO userVO);
    public UserVO showPoint(Long userNumber);
    public int removeService(Long userNumber);
}
