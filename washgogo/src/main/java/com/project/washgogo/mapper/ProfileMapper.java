package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.ProfileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfileMapper {
    public void insert(ProfileVO profileVO);
    public ProfileVO select(Long userNumber);
    public void delete(Long userNumber);
    public List<ProfileVO> getOldFiles();
}
