package com.example.happypetsday.service.pet;

import com.example.happypetsday.dto.PetDto;
import com.example.happypetsday.mapper.PetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {
    private final PetMapper petMapper;


    /**
     * 회원번호로 펫 리스트 가져오기
     * @param userNumber
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    public List<PetDto> findPet(Long userNumber){
        if(userNumber==null){
            throw new IllegalArgumentException("펫 조회 회원 번호 누락");
        }
        return petMapper.selectUsersPet(userNumber);
    }

    /**
     * 펫 정보 등록하기
     * @param petDto
     * @throws IllegalArgumentException 펫 정보, 회원번호 누락시 발생
     */
    public void addPet(PetDto petDto){
        if(petDto == null){
            throw new IllegalArgumentException("펫, 회원정보 누락");
        }
        petMapper.insertPet(petDto);
    }



}














