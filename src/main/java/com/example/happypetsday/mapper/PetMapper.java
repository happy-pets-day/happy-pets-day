package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.PetDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PetMapper {

//    회원번호로 펫 정보 불러오기
    List<PetDto> selectUsersPet(Long userNumber);
}
