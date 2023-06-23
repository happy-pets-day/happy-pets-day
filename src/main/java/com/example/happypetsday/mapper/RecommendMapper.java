package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.RecommendDto;
import com.example.happypetsday.vo.RecommendVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommendMapper {
//    추천 결과 리스트 반환
    List<RecommendVo> selectRecommendList(RecommendDto recommendDto);
//    추천 결과 상세정보 반환
    RecommendVo selectRecommendByNumber(Long recommendNumber);
}
