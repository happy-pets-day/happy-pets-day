package com.example.happypetsday.service.admin;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.mapper.AdminMapper;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.UserVo;
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
    public UserVo findUser(Long userNumber){
        if(userNumber==null){
            throw new IllegalArgumentException("회원번호가 없습니다.");
        }
        return Optional.ofNullable(adminMapper.selectUserOne(userNumber))
                .orElseThrow(()->{ throw new IllegalArgumentException("존재하지 않는 회원입니다.");});
    }

    //    전체 조회
    @Transactional(readOnly = true)
    public List<UserVo> findAll(Criteria criteria){
        return adminMapper.selectAllUser(criteria);
    }

    //    전체 게시글 수 조회
    @Transactional(readOnly = true)
    public int getTotal(){
        return adminMapper.selectTotal();
    }

    // 회원등급 String으로 반환
    public String viewStatus(int userStatus){
        String userStatusResult;

        switch (userStatus){
            case 0:
//              관리자
                userStatusResult="관리자";
                break;
            case -1:
//               탈퇴or제명회원
                userStatusResult="탈퇴or제명회원";
                break;
            case 2:
//                펫시터 신청중인 회원
                userStatusResult="펫시터 신청중";
                break;
            case 3:
//                펫시터 회원
                userStatusResult="펫시터 회원";
                break;
            default:
                userStatusResult="일반 회원";
        }
        return userStatusResult; // userStatusResult 반환
    }


    // 수정(회원 제명-회원등급 변경)
    public void modify(UserDto userDto){
        if(userDto == null){
            throw new IllegalArgumentException("회원 정보가 없습니다.");
        }
        adminMapper.update(userDto);
    }





}








