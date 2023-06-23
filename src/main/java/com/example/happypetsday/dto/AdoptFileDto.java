package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AdoptFileDto {
    int ANIMAL_NO;  // 동물번호
    String PHOTO_KND;   // 사진 종류
    int PHOTO_NO;   // 사진 번호
    String PHOTO_URL;   // 사진 URL
}