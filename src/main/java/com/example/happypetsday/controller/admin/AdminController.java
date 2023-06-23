package com.example.happypetsday.controller.admin;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.service.admin.AdminService;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.PageVo;
import com.example.happypetsday.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        UserVo userVo = adminService.findUser(userNumber);

        userVo.setUserStatusResult(adminService.viewStatus(userVo.getUserStatus()));

        model.addAttribute("user", userVo);
        return "admin/userDetailManage";
    }

    @GetMapping("/userManage")
    public String userManage(Criteria criteria, Model model){
        List<UserVo> userList = adminService.findAll(criteria);

        List<UserVo> resultList = userList.stream()
                .map(user -> {
                    user.setUserStatusResult(adminService.viewStatus(user.getUserStatus()));
                    return user;
                })
                .collect(Collectors.toList());


//        List<UserVo> resultList = new ArrayList<>();
//
//        for(UserVo user: userList){
//            user.setUserStatusResult(adminService.viewStatus(user.getUserStatus()));
//            resultList.add(user);
//        }



        model.addAttribute("userList", resultList);
        model.addAttribute("pageInfo", new PageVo(criteria, adminService.getTotal()));

        return "admin/userManage";
    }

    @GetMapping("/viewApplication")
    public String viewApplication(){
        return "admin/viewApplication";
    }



}
