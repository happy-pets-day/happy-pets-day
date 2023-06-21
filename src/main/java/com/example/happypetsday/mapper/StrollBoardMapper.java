package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.StrollBoardDto;
import com.example.happypetsday.vo.StrollBoardVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StrollBoardMapper {
//    게시글 등록
    void insert(StrollBoardDto strollBoardDto);
//    게시글 1개 조회
    StrollBoardVo selectOnd(Long strollBoardNumber);
//    게시글 조회수 업데이트
    void updateViewCount(Long strollBoardNumber);
//    전체 게시글 수 조회
    int selectTotal();


}














