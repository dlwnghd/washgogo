package com.project.washgogo.domain.dao;

import com.project.washgogo.domain.vo.OrderListVO;
import com.project.washgogo.domain.vo.OrderVO;
import com.project.washgogo.domain.vo.UserVO;
import com.project.washgogo.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderDAO {
    private final OrderMapper orderMapper;

    //    주문내역 가져오기
    public List<OrderVO> getList() { return orderMapper.getList(); }
    //    이용 내역(제일 최신 수거 조회)
    public OrderVO getRecent(Long userNumber){
        return orderMapper.selectRecentRequest(userNumber);
    }
    //    수거 신청
    public void applyRequest(OrderVO orderVO) {
        orderMapper.applyRequest(orderVO);
    }
    //    총 금액 구하기
    public int getTotalPrice() { return orderMapper.getTotalPrice(); }
    //    수거 취소
    public boolean removeRequest(Long orderNumber) { return orderMapper.delete(orderNumber) == 1;}
}
