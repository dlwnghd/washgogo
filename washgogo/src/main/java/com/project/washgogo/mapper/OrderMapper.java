package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
//    ?
    public List<OrderVO> getList();
}
