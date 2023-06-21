package com.example.happypetsday.service.user;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
@Transactional
class UserServiceTest {
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;
    private UserDto userDto;

    @BeforeEach
    void setUp(){
    userDto = new UserDto();
    userDto.setUserName("이건희");
    userDto.setUserId("aaa");
    userDto.setUserPassword("1234");
    userDto.setUserGender("M");
    userDto.setUserEmail("aaa@naver.com");
    userDto.setUserBirth("1995.11.24");
    userDto.setUserPhoneNumber("01011111111");
    userDto.setUserAddress("강남구");
    }

    @Test
    void findUserInfoByUserNumber() {
        doReturn(userDto).when(userMapper).selectUserInfoByUserNumber(any(Long.class));
        assertThat("1234").isEqualTo(userService.findUserInfoByUserNumber(1L).getUserPassword());
    }
}