package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    //회원(1명) 상세 조회
    UserVo selectUserOne(Long userNumber);

    //회원 전체 조회
    public List<UserVo> selectAllUser(Criteria criteria);

    //전체 게시글 수 조회
    public int selectTotal();

    // 회원 삭제(제명)
    public void update(UserDto userDto);
}
