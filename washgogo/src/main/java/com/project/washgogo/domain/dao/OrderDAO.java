package com.project.washgogo.domain.dao;

import com.project.washgogo.domain.vo.OrderListVO;
import com.project.washgogo.domain.vo.OrderVO;
import com.project.washgogo.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDAO {
    private final OrderMapper orderMapper;

    //    주문내역 가져오기
    public List<OrderVO> getList() { return orderMapper.getList(); }
    //    수거 신청
    public void insert(OrderVO orderVO, OrderListVO orderListVO) { orderMapper.insert(orderVO, orderListVO);}
    //    이용 내역
    public OrderVO select(Long orderNumber) { return orderMapper.select(orderNumber);}
    //    총 수량 구하기
    public int getTotalQuantity() { return orderMapper.getTotalQuantity();}
    //    총 금액 구하기
    public int getTotalPrice() { return orderMapper.getTotalPrice(); }
    //    수거 취소
    public void delete(Long orderNumber) { orderMapper.delete(orderNumber);}
}
