package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.OrderListVO;
import com.project.washgogo.domain.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderListMapper {
//    리스트 가져오기
    public List<OrderListVO> getList();

    //    주문내역 리스트
    public List<OrderListVO> getRecentList(Long orderNumber);

    //    주문상세내역 추가
    public void insertShipping(Long orderNumber);

}
