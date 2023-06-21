package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.StrollBoardDto;
import com.example.happypetsday.vo.StrollBoardVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
@Slf4j
@Transactional
class StrollBoardMapperTest {

    @Autowired
    private StrollBoardMapper strollBoardMapper;
    private StrollBoardDto strollBoard;

    @BeforeEach
    void setUp() {
        strollBoard = new StrollBoardDto();
        strollBoard.setStrollBoardTitle("테스트제목");
        strollBoard.setStrollBoardContent("테스트내용");
        strollBoard.setStrollBoardLat(3.13);
        strollBoard.setStrollBoardLng(65.13323);
        strollBoard.setStrollBoardMeetingDate("2023-06-11");
        strollBoard.setStrollBoardMeetingPlace("테스트공원 앞");
        strollBoard.setStrollBoardAdminDistrict("행정구역");
        strollBoard.setPetNumber(1L);
        strollBoard.setStrollBoardAdminDistrict("테스트내용");

        strollBoard.setUserNumber(31L);
    }

    @Test
    @DisplayName("게시글 등록 및 조회")
    void insertAndSelectOnd() {
        strollBoardMapper.insert(strollBoard);

        StrollBoardVo findBoard = strollBoardMapper.selectOnd(strollBoard.getStrollBoardNumber());

        assertThat(findBoard.getStrollBoardNumber()).isEqualTo(strollBoard.getStrollBoardNumber());
    }
}