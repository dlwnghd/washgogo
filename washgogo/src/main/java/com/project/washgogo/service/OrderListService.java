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
    private OrderListDAO orderListDAO;

    public List<OrderListVO> getList() {
        return orderListDAO.getList();
    }
    public void addList(OrderListVO orderListVO, OrderVO orderVO) {
        orderListDAO.addList(orderListVO, orderVO);
    }
    public OrderListVO getLog(Long orderNumber) {
        return OrderListVO.getLog(orderNumber);
    }
}
