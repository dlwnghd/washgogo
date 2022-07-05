package com.project.washgogo.service;

import com.project.washgogo.domain.dao.OrderListDAO;
import com.project.washgogo.domain.vo.OrderListVO;
import com.project.washgogo.domain.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderListService {
    private final OrderListDAO orderListDAO;

    public List<OrderListVO> getList() {
        return orderListDAO.getList();
    }

    public List<OrderListVO> getRecentList(Long orderNumber) {
        return orderListDAO.getRecentList(orderNumber);
    }

    public void insertWasher1(Long orderNumber) {
        orderListDAO.insertWasher1(orderNumber);
    }

    public void insertWasher2(Long orderNumber, Long orderEAQuantity) {
        orderListDAO.insertWasher2(orderNumber, orderEAQuantity);
    }

    public void insertCleaning(Long orderNumber, Long orderEAQuantity) {
        orderListDAO.insertCleaning(orderNumber, orderEAQuantity);
    }

    public void insertShipping(Long orderNumber) {
        orderListDAO.insertShipping(orderNumber);
    }

}
