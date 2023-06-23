package com.example.happypetsday.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class RecommendVo {
    private Long recommendNumber;
    private String recommendName;
    private String recommendFileName;
    private String recommendSpecies;
    private String recommendPersonality;
    private String recommendColor;
    private String recommendLength;
    private String recommendSize;
    private String recommendAlopecia;
    private String recommendContent1;
    private String recommendContent2;
    private String recommendContent3;
}
