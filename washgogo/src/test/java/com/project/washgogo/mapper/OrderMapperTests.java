package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class OrderMapperTests {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void getListTest(){
        orderMapper.getList().stream().map(OrderVO::toString).forEach(log::info);
    }

}















