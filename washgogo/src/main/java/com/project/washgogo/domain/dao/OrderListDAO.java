package com.project.washgogo.domain.dao;

import com.project.washgogo.domain.vo.OrderListVO;
import com.project.washgogo.domain.vo.OrderVO;
import com.project.washgogo.mapper.OrderListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderListDAO {
    private final OrderListMapper orderListMapper;

    //    리스트 가져오기
    public List<OrderListVO> getList() { return orderListMapper.getList();}
    //    주문상세내역 추가
    public void addList(OrderListVO orderListVO, OrderVO orderVO) { orderListMapper.insert(orderListVO, orderVO);}
    //    이용 내역
    public OrderListVO getLog(Long orderNumber) { return orderListMapper.select(orderNumber);}
}