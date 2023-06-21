package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Slf4j
class AdminMapperTest {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserNumber(1L);
    }

    @Test
    @DisplayName("회원 1명 상세정보 조회")
    void selectUserOne() {
    UserDto finduser =adminMapper.selectUserOne(userDto.getUserNumber());
    log.info(finduser.toString());

        assertThat(finduser.getUserNumber())
                .isEqualTo(userDto.getUserNumber());
    }

    @Test
    @DisplayName("회원 전체 조회")
    void selectAllUser() {
        int beforeSize = adminMapper.selectAllUser().size();
        assertThat(adminMapper.selectAllUser().size()).isEqualTo(beforeSize+1);
    }

}