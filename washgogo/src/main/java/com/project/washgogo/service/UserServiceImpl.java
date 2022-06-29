package com.project.washgogo.service;

import com.project.washgogo.domain.dao.UserDAO;
import com.project.washgogo.domain.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("user")
@Primary
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;
    @Override
    public List<UserVO> getList() {
        return userDAO.getList();
    }

    @Override
    public void join(UserVO userVO) { userDAO.join(userVO); }

    @Override
    public Long login(String userEmail, String userPw){
        return userDAO.selectUser(userEmail,userPw);
    }

    @Override
    public UserVO myPageInfo(Long userNumber) {  return userDAO.myPageInfo(userNumber);  }

    @Override
    public UserVO loadUserInfo(Long userNumber) {
        return userDAO.loadUserInfo(userNumber);
    }

    @Override
    public boolean modifyUserInfo(UserVO userVO) {
        return userDAO.modifyUserInfo(userVO);
    }

    @Override
    public boolean changeService(UserVO userVO) {
        return userDAO.changeService(userVO);
    }

    @Override
    public boolean modifyAddress(UserVO userVO) {
        return userDAO.modifyAddress(userVO);
    }

    @Override
    public boolean resignMember(Long userNumber) {
        return userDAO.resignMember(userNumber);
    }

    @Override
    public boolean checkEmail(String userEmail){ return userDAO.checkEmail(userEmail);}
}
