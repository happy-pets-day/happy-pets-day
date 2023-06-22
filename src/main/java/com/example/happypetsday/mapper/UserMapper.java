package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
//    회원등록(회원가입)
     void insert(UserDto userDto);
//    회원번호,상태 조회(로그인)
     UserDto selectUserNumberAndStatus(@Param("userId")String userId, @Param("userPassword")String userPassword);
}
