package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AdoptDto {
    int ANIMAL_NO; // 동물 번호
    String NM;  // 이름
    String ENTRNC_DATE; // 입소날짜
    String SPCS; // 종
    String BREEDS; // 품종
    String SEXDSTN; // 성별
    String AGE; // 나이
    double BDWGH;  // 체중
    String ADP_STTUS;  // 입양상태
    String TMPR_PRTC_STTUS; // 임시보호 상태
    String INTRCN_MVP_URL;  // 소개영상 URL
    String INTRCN_CN;  // 소개내용
    String TMPR_PRTC_CN;  // 임시보호내용
}