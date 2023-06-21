package com.example.happypetsday.controller.admin;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.service.admin.AdminService;
import com.example.happypetsday.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/applicationManage")
    public String applicationManage() {
        return "admin/applicationManage";
    }

    @GetMapping("/petsitterDetailView")
    public String petsitterDetailView(){
        return "admin/petsitterDetailView";
    }

    @GetMapping("/petsitterManage")
    public String petsitterManage(){
        return "admin/petsitterManage";
    }

    @GetMapping("/strollBoardManage")
    public String strollBoardManage(){
        return "admin/strollBoardManage";
    }

    @GetMapping("/userDetailManage")
    public String userDetailManage(Long userNumber, Model model){
        UserDto userDto = adminService.findUser(userNumber);
        String userStatus;

        switch (userDto.getUserStatus()){
            case 0:
//              관리자
                userStatus="일반 회원";
                break;
            case -1:
//               탈퇴or제명회원
                userStatus="탈퇴or제명회원";
                break;
            case 2:
//                펫시터 신청중인 회원
                userStatus="펫시터 신청중";
                break;
            case 3:
//                펫시터 회원
                userStatus="펫시터 회원";
                break;
            default:
                userStatus="일반 회원";
        }
        model.addAttribute("user", userDto);
        model.addAttribute("userStatus", userStatus);
        return "admin/userDetailManage";
    }

    @GetMapping("/userManage")
    public String userManage(Criteria criteria, Model model){
        List<UserDto> userList = adminService.findAll(criteria);
        model.addAttribute("userList", userList);
        model.addAttribute("pageInfo", new PageVo(criteria, adminService.getTotal()));

        return "admin/userManage";
    }

    @GetMapping("/viewApplication")
    public String viewApplication(){
        return "admin/viewApplication";
    }
}
