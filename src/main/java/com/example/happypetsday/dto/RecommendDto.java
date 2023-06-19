package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class RecommendDto {
    private Long recommendNumber;
    private String recommendName;
    private String recommendSpecies;
    private String recommendPersonality;
    private String recommendColor;
    private String recommendLength;
    private String recommendSize;
    private String recommendAlopecia;
    private String recommendContent;
}
