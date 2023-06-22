package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterApplyLicenseFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitterApplyLicenseFileMapper {
    void insert(SitterApplyLicenseFile sitterApplyLicenseFile);
    void delete(Long applyNumber);
    List<SitterApplyLicenseFile> select(Long applyNumber);
    List<SitterApplyLicenseFile> selectOldList();
}
