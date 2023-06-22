package com.example.happypetsday.service.sitter;

import com.example.happypetsday.dto.SitterApplyDto;
import com.example.happypetsday.mapper.SitterApplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SitterApplyService {
    private final SitterApplyMapper sitterApplyMapper;

    public void register(SitterApplyDto sitterApplyDto) {
        if(sitterApplyDto==null){throw new IllegalArgumentException("회원정보 누락");}
        sitterApplyMapper.apply(sitterApplyDto);

    }



}
