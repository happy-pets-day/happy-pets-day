package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterReviewDto {
    private Long reviewNumber;
    private Long userNumber;
    private Long sitterNumber;
    private Long reservationNumber;
    private String reviewContent;
    private String reviewDate;
    private double reviewScore;
}
