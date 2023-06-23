package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.PetDto;
import com.example.happypetsday.dto.PetFileDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PetFileMapper {
    void insert(PetFileDto petFileDto);
    void delete(Long petNumber);
    PetFileDto select(Long petNumber);


}
