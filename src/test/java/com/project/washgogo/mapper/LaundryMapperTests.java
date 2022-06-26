package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.LaundryVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class LaundryMapperTests {
    @Autowired
    private LaundryMapper laundryMapper;

    @Test
    public void getListTest(){
        laundryMapper.getList().stream().map(LaundryVO::toString).forEach(log::info);
    }

}















