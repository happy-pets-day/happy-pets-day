package com.example.happypetsday.service.recommend;

import com.example.happypetsday.dto.RecommendDto;
import com.example.happypetsday.mapper.RecommendMapper;
import com.example.happypetsday.vo.RecommendVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommendService {
    private final RecommendMapper recommendMapper;

    @Transactional(readOnly = true)
    public List<RecommendVo> findRecommendList(RecommendDto recommendDto){
        if(recommendDto == null){
            throw new IllegalArgumentException("추천 정보 누락");
        }
        if(recommendDto.getRecommendAlopecia().equals("상관없음")){
            recommendDto.setRecommendAlopecia("%");
        }
        return recommendMapper.selectRecommendList(recommendDto);
    }

    @Transactional(readOnly = true)
    public RecommendVo findRecommendDetail(Long recommendNumber){
        if(recommendNumber == null){
            throw new IllegalArgumentException("추천 정보 누락");
        }
        return recommendMapper.selectRecommendByNumber(recommendNumber);
    }
}
