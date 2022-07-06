package com.project.washgogo.domain.dao;

import com.project.washgogo.domain.vo.PriceVO;
import com.project.washgogo.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
// RDB에 가깝던 mapper 인터페이스를
// 객체에 가깝게 변경시켜주는 역할
@RequiredArgsConstructor
public class PriceDAO {
    private final PriceMapper priceMapper;
    public void add(PriceVO priceVO){priceMapper.insert(priceVO);
    }
}
