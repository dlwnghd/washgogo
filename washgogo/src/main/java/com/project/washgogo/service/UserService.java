package com.project.washgogo.service;

import com.project.washgogo.domain.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserVO> getList();
    public void join(UserVO userVO);
    public Long login(String userEmail, String userPw);
<<<<<<< HEAD
=======
    public UserVO myPageInfo(Long userNumber);
>>>>>>> 4bd22e1459bfb95bffe20a68877b0e167557b849
    public UserVO loadUserInfo(Long userNumber);
    public boolean modifyUserInfo(UserVO userVO);
    public boolean changeService(UserVO userVO);
    public boolean modifyAddress(UserVO userVO);
    public boolean resignMember(Long userNumber);
    public boolean checkEmail(String userEmail);
}
