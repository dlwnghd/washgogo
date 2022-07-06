package com.project.washgogo.service;

import com.project.washgogo.domain.dao.ProfileDAO;
import com.project.washgogo.domain.dao.UserDAO;
import com.project.washgogo.domain.vo.ProfileVO;
import com.project.washgogo.domain.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("user")
@Primary
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;
    private final ProfileDAO profileDAO;
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
    @Transactional(rollbackFor = Exception.class)
    public void modifyProfile(UserVO userVO){
        if(userVO.getProfile() != null){
            userVO.getProfile().setUserVO(userVO);
            profileDAO.remove(userVO.getUserNumber());
            profileDAO.save(userVO.getProfile());
        }
    }

    @Override
    public void removeProfile(Long userNumber){
        profileDAO.remove(userNumber);
    }

    @Override
    public ProfileVO getProfile(Long userNumber){
        return profileDAO.findByUserNumber(userNumber);
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
    public boolean resignMember(Long userNumber) { return userDAO.resignMember(userNumber); }

    @Override
    public boolean checkEmail(String userEmail){ return userDAO.checkEmail(userEmail);}

    @Override
    public boolean checkUser(UserVO userVO) {return userDAO.checkUser(userVO); }

    @Override
    public int findUserNumber(UserVO userVO) {return userDAO.findUserNumber(userVO); }

    @Override
    public void changePw(UserVO userVO) {userDAO.changePw(userVO); }

    @Override
    public UserVO showPoint(Long userNumber) { return userDAO.showPoint(userNumber); }

    @Override
    public int removeService(Long userNumber) {
        return userDAO.removeService(userNumber);
    }
}
