package com.project.washgogo.mapper;

import com.project.washgogo.domain.vo.Criteria;
import com.project.washgogo.domain.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    //    게시글 목록
    public List<NoticeVO> getList(Criteria criteria);
    //    게시글 추가
    public void insert(NoticeVO noticeVO);
    //    게시글 한 개 가져오기
    public NoticeVO select(Long NoticeNumber);
    //    게시글 수정
    //public int update(BoardVO boardVO);
    //    게시글 삭제
    //public int delete(Long boardNumber);
    //    게시글 전체 개수
    //public int getTotal(Criteria criteria);
}
