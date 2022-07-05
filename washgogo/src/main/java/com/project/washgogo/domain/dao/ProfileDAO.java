package com.project.washgogo.domain.dao;

import com.project.washgogo.domain.vo.ProfileVO;
import com.project.washgogo.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProfileDAO {

    private final ProfileMapper profileMapper;

    public void save(ProfileVO profileVO){
        profileMapper.insert(profileVO);
    }

    public List<ProfileVO> findByBoardNumber(Long userNumber){
        return profileMapper.select(userNumber);
    }

    public void remove(Long userNumber){ profileMapper.delete(userNumber); }

    public List<ProfileVO> getOldFiles(){
        return profileMapper.getOldFiles();
    }
}
