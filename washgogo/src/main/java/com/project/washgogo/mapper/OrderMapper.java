package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.OrderListVO;
import com.project.washgogo.domain.vo.OrderVO;
import com.project.washgogo.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
//    주문내역 가져오기
    public List<OrderVO> getList();
//    수거 신청
    public void insert(OrderVO orderVO, OrderListVO orderListVO);
//    이용 내역
    public OrderVO selectRecentRequest(Long userNumber);
//    총 수량 구하기
    public int getTotalQuantity();
//    총 금액 구하기
    public int getTotalPrice();
//    수거 취소
    public int delete(Long orderNumber);

}
