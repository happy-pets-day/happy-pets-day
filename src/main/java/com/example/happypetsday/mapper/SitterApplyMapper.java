package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterApplyDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SitterApplyMapper {
    void apply(SitterApplyDto sitterApplyDto);
    Long selectApplyNumber(@Param("userNumber")Long userNumber);
}
