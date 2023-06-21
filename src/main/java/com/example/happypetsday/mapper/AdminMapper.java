package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    //회원(1명) 상세 조회
    UserDto selectUserOne(Long userNumber);

    //회원 전체 조회
    public List<UserDto> selectAllUser(Criteria criteria);

    //전체 게시글 수 조회
    public int selectTotal();
}
