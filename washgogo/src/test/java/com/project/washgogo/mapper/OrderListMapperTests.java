package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.OrderListVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class OrderListMapperTests {
    @Autowired
    private OrderListMapper orderListMapper;

    @Test
    public void getListTest(){
        orderListMapper.getList().stream().map(OrderListVO::toString).forEach(log::info);
    }

}















