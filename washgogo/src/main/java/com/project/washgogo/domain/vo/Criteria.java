package com.project.washgogo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
//@RequiredArgsConstructor //기본 생성자보다 우선순위가 낮다.
@AllArgsConstructor
public class Criteria {
    private Integer pageNum;
    private Integer amount;
    private String type;
    private String keyword;

    public Criteria() {
        this(1, 10);
    }

    public Criteria(Integer pageNum, Integer amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public String[] getTypes(){
        return type == null ? new String[]{} : type.split("");
    }
}
