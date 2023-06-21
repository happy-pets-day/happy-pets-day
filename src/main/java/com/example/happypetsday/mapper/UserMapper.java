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
//     아이디 중복검사
     int countByUserId(@Param("userId") String userId);
//     마이페이지 비밀번호 검사
     String selectUserPasswordByUserNumber(@Param("userNumber")Long userNumber);
//     회원 번호로 회원 정보 가져오기(마이페이지용)
     UserDto selectUserInfoByUserNumber(@Param("userNumber")Long userNumber);
//     마이페이지 회원정보 수정
     void update(UserDto userDto);
}
