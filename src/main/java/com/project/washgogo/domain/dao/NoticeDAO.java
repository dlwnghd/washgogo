package com.project.washgogo.domain.dao;

import com.project.washgogo.domain.vo.NoticeVO;
import com.project.washgogo.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// RDB에 가깝던 mapper 인터페이스를
// 객체에 가깝게 변경시켜주는 역할
@RequiredArgsConstructor
public class NoticeDAO {
    private final NoticeMapper noticeMapper;

    //    게시글 목록
    //public List<BoardVO> getList(Criteria criteria){
    //    return boardMapper.getList(criteria);
    //}
    //    게시글 추가
    public void add(NoticeVO noticeVO){
        noticeMapper.insert(noticeVO);
    }
    //    게시글 한 개 가져오기
    public NoticeVO findByNoticeNumber(Long noticeNumber){
        return noticeMapper.select(noticeNumber);
    }
    //    게시글 수정
    //public boolean modify(BoardVO boardVO){
    //    return boardMapper.update(boardVO) == 1;
    //}
    //    게시글 삭제
    //public boolean remove(Long boardNumber){
    //    return boardMapper.delete(boardNumber) == 1;
    //}
    //    게시글 전체 개수
    //public int getTotal(Criteria criteria){
    //    return boardMapper.getTotal(criteria);
    //}

}
