package com.project.washgogo.domain.dao;

import com.project.washgogo.domain.vo.LaundryVO;
import com.project.washgogo.mapper.LaundryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LaundryDAO {
    private final LaundryMapper laundryMapper;

    //    가격표 목록
    public List<LaundryVO> getList() { return laundryMapper.getList();}
    //    의류 하나의 정보 가져오기
    public LaundryVO getLaundryInfo(Long laundryNumber) { return laundryMapper.selectlaundryInfo(laundryNumber);}
}
