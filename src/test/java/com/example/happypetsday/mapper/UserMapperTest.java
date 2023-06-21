package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.UserDto;
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
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new  UserDto();
        userDto.setUserId("test");
        userDto.setUserPassword("1234");
        userDto.setUserName("홍길동");
        userDto.setUserGender("M");
        userDto.setUserEmail("test@test.com");
        userDto.setUserBirth("2023-06-15");
        userDto.setUserPhoneNumber("010-1234-1234");
        userDto.setUserAddress("인천 중구");
        userDto.setQuestionNumber(2);
        userDto.setFindPasswordAnswer("강아지");
        userDto.setUserZoneCode(467);
        userDto.setUserAddressDetail("303호");
        userDto.setUserAddressTip("면목동");
    }

    @Test
    @DisplayName("회원 등록 테스트")
    void insert() {
        userMapper.insert(userDto);

        UserDto findUser = userMapper.selectUserNumberAndStatus(userDto.getUserId(),userDto.getUserPassword());

        assertThat(userDto.getUserNumber()).isEqualTo(findUser.getUserNumber());
        assertThat(findUser.getUserStatus()).isEqualTo(1);
    }

    @Test
    @DisplayName("비밀번호 검사 테스트")
    void selectUserPasswordByUserNumber(){
        assertThat(userMapper.selectUserPasswordByUserNumber(2L)).isEqualTo("1234");
    }

    @Test
    @DisplayName("유저 정보 뽑아오기 마이페이지용")
    void selectUserInfoByUserNumber(){
        assertThat(userMapper.selectUserInfoByUserNumber(2L).getUserPassword()).isEqualTo("1234");
    }
}