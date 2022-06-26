package com.project.washgogo.service;

import com.project.washgogo.domain.vo.Criteria;
import com.project.washgogo.domain.vo.NoticeVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeService {
    public List<NoticeVO> getList(Criteria criteria);
    public void add(NoticeVO noticeVO);
    public NoticeVO get(Long noticeNumber);
    //public boolean modify(BoardVO boardVO);
    //public boolean remove(Long boardNumber);
    //public int getTotal(Criteria criteria);
}
