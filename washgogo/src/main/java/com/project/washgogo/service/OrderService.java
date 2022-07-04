package com.project.washgogo.service;

import com.project.washgogo.domain.dao.OrderDAO;
import com.project.washgogo.domain.vo.OrderListVO;
import com.project.washgogo.domain.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderDAO orderDAO;

    public List<OrderVO> getList() {
        return orderDAO.getList();
    }
    public void requestApply(OrderVO orderVO, OrderListVO orderListVO) {
        orderDAO.applyRequest(orderVO, orderListVO);
    }
    public OrderVO getRecent(Long userNumber) {
        return orderDAO.getRecent(userNumber);
    }
    public int getTotalQuantity() {
        return orderDAO.getTotalQuantity();
    }
    public int getTotalPrice() {
        return orderDAO.getTotalPrice();
    }
    public boolean removeRequest(Long orderNumber) {
        return orderDAO.removeRequest(orderNumber);
    }
}
