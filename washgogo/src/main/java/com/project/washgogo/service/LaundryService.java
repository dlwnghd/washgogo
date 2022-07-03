package com.project.washgogo.service;

import com.project.washgogo.domain.dao.LaundryDAO;
import com.project.washgogo.domain.vo.LaundryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LaundryService {
    private final LaundryDAO laundryDAO;

    public List<LaundryVO> getList() {
        return laundryDAO.getList();
    }
    public LaundryVO getLaundryInfo(Long laundryNumber){
        return laundryDAO.getLaundryInfo(laundryNumber);
    }
}
