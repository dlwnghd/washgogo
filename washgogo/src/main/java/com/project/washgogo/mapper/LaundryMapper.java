package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.LaundryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LaundryMapper {
//    가격표 목록
    public List<LaundryVO> getList();
//    의류 하나의 정보 가져오기
    public LaundryVO selectlaundryInfo(Long laundryNumber);
}
