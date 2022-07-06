package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.PriceVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PriceMapper {
    //    게시글 추가
    public void insert(PriceVO priceVO);
}
