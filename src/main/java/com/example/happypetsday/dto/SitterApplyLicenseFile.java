package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterApplyLicenseFile {
    private Long applyFileNumber;
    private String applyFileTitle;
    private String applyFileName;
    private String applyFileUuid;
    private String applyFileUploadPath;
    private Long applyNumber;
    private Long userNumber;
}
