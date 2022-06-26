package com.project.washgogo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class PageDTO {
    private Integer startPage;
    private Integer endPage;
    private Integer realEnd;
    private boolean next, prev;
    private Integer total;
    private Criteria criteria;

    public PageDTO(Criteria criteria, Integer total){
        this.criteria = criteria;
        this.total = total;

        this.endPage = (int)(Math.ceil(criteria.getPageNum() / (double)criteria.getAmount())) * criteria.getAmount();
        this.startPage = this.endPage - 9;
        this.realEnd = (int)Math.ceil(total / (double)criteria.getAmount());

        if(realEnd < this.endPage){
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }

}
