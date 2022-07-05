package com.project.washgogo.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ServiceAreaVO {
    private String provinceName;
    private String cityName;
    private String newTownName;
    private String guName;
    private String dongName;
}
