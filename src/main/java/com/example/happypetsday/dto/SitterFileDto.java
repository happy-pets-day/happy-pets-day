package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterFileDto {
    private Long sitterFileNumber;
    private Long sitterNumber;
    private String sitterFileFileName;
    private String sitterFileUuid;
    private String sitterFileUploadPath;
}
