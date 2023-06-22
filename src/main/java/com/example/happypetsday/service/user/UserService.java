package com.example.happypetsday.service.user;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

//    회원등록
    public void register(UserDto userDto){
        if(userDto==null){
            throw new IllegalArgumentException("회원정보 누락");
        }
        userMapper.insert(userDto);
    }

    /**
     * 회원번호, 상태(일반회원,관리자,탈퇴회원) 조회하기
     * @param userId
     * @param userPassword
     * @throws IllegalArgumentException 존재하지 않는 회원 id, pw로 조회하는 경우
     */
    @Transactional(readOnly = true)
    public UserDto findUserNumberAndStatus(String userId, String userPassword){
        if(userId==null||userPassword==null){
            throw new IllegalArgumentException("아이디 또는 패스워드 누락");
        }
        return Optional.ofNullable(userMapper.selectUserNumberAndStatus(userId, userPassword))
                .orElseThrow(()->{ throw new IllegalArgumentException("존재하지 않는 회원입니다.");});
    }




}
