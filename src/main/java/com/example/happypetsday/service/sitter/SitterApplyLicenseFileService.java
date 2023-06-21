package com.example.happypetsday.service.sitter;


import com.example.happypetsday.dto.SitterApplyLicenseFile;
import com.example.happypetsday.mapper.SitterApplyLicenseFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SitterApplyLicenseFileService {
    private final SitterApplyLicenseFileMapper licenseFile;

    @Value("${file.dir}")
    private String fileDir;

    public void register(SitterApplyLicenseFile file){
        if(file == null) { throw new IllegalArgumentException("파일 정보 누락"); }
        licenseFile.insert(file);
    }

    public void remove(Long applyNumber){
        if(applyNumber == null){throw new IllegalArgumentException("지원 번호 누락");}



    }
}
