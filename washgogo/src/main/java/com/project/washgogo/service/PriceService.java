package com.project.washgogo.service;

import com.project.washgogo.domain.vo.PriceVO;
import org.springframework.stereotype.Service;

@Service
public interface PriceService {
    public void add(PriceVO priceVO);
}
