package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
@Slf4j
class AdminMapperTest {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;

    private UserVo userVo;
    private UserDto userDto;
    private Criteria criteria;

    @BeforeEach
    void setUp() {
        criteria = new Criteria();
        userVo = new UserVo();
        userVo.setUserNumber(1L);

        userDto = new UserDto();
        userDto.setUserNumber(1L);
        userDto.setUserStatus(1);

    }

    @Test
    @DisplayName("회원 1명 상세정보 조회")
    void selectUserOne() {
    UserVo finduser =adminMapper.selectUserOne(userVo.getUserNumber());
    log.info(finduser.toString());

        assertThat(finduser.getUserNumber())
                .isEqualTo(userVo.getUserNumber());
    }

    @Test
    @DisplayName("회원 전체 조회")
    void selectAllUser() {
        int beforeSize = adminMapper.selectAllUser(criteria).size();

        assertThat(adminMapper.selectAllUser(criteria).size()).isGreaterThan(0);
        assertThat(adminMapper.selectTotal()).isGreaterThan(0);
    }

    @Test
    @DisplayName("회원 삭제(제명)")
    void update() {
        userDto.setUserStatus(-1);

        adminMapper.update(userDto);

        assertThat(adminMapper.selectUserOne(userDto.getUserNumber()).getUserStatus())
                .isEqualTo(userDto.getUserStatus());
    }


}