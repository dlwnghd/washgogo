package com.project.washgogo.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PriceVO {
    private Long priceNumber;
    private String item;
    private Long amount;
    private Long price;
    private Long userNumber;
    private Long pointUse;
}
