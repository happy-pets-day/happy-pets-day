package com.example.happypetsday.service.admin;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.mapper.AdminMapper;
import com.example.happypetsday.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    /**
     * 회원 1명 상세정보 조회
     * @param userNumber
     * @throws IllegalArgumentException 존재하지 않는 회원 userNumber로 조회하는 경우
     */
    @Transactional(readOnly = true)
    public UserDto findUser(Long userNumber){
        if(userNumber==null){
            throw new IllegalArgumentException("회원번호가 없습니다.");
        }
        return Optional.ofNullable(adminMapper.selectUserOne(userNumber))
                .orElseThrow(()->{ throw new IllegalArgumentException("존재하지 않는 회원입니다.");});
    }

    //    전체 조회
    @Transactional(readOnly = true)
    public List<UserDto> findAll(Criteria criteria){
        return adminMapper.selectAllUser(criteria);
    }

    //    전체 게시글 수 조회
    @Transactional(readOnly = true)
    public int getTotal(){
        return adminMapper.selectTotal();
    }

}
