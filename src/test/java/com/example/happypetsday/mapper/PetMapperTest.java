package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.PetDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
class PetMapperTest {

    @Autowired
    private PetMapper petMapper;
    private PetDto petDto;

    @BeforeEach
    void setUp() {
        petDto = new PetDto();
        petDto.setUserNumber(31L);
    }

    @Test
    @DisplayName("회원번호로 펫 리스트 조회")
    void selectUsersPet() {
        List<PetDto> petList = petMapper.selectUsersPet(petDto.getUserNumber());

        assertThat(petList.size()).isNotNull();
    }
}