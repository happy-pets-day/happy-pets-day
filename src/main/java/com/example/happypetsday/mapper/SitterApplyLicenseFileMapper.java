package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterApplyLicenseFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SitterApplyLicenseFileMapper {
    void insert(SitterApplyLicenseFile sitterApplyLicenseFile);
    void delete(Long applyNumber);
    SitterApplyLicenseFile select(Long applyNumber);
}
