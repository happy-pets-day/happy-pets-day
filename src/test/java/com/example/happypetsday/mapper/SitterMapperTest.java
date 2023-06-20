package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterApplyDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class SitterMapperTest {

    @Autowired
    private SitterMapper sitterMapper;
    private SitterApplyDto sitterApplyDto;

    @BeforeEach
    void setUp(){
        sitterApplyDto = new SitterApplyDto();

        sitterApplyDto.setUserNumber(3L);
        sitterApplyDto.setApplyContent("asd");
    }

    @Test
    void apply() {
        sitterMapper.apply(sitterApplyDto);
    }
}