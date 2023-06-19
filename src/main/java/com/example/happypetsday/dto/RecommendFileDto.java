package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class RecommendFileDto {
    private Long recommendFileNumber;
    private Long recommendNumber;
    private String recommendFileName;
    private String recommendFileUuid;
    private String recommendFileUploadPath;
}
