package com.project.washgogo.service;

import com.project.washgogo.domain.dao.PriceDAO;
import com.project.washgogo.domain.vo.PriceVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("order")
@Primary
public class PriceServiceImpl implements PriceService  {
    private final PriceDAO priceDAO;

    @Override
    public void add(PriceVO priceVO) {priceDAO.add(priceVO);}
}
