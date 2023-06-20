package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterApplyDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SitterMapper {
    void apply(SitterApplyDto sitterApplyDto);
}
