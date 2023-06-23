package com.example.happypetsday.controller.myPage;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/myPage/*")
public class MyPageController {
    private final UserService userService;

    @GetMapping("/addPet")
    public String addPet(){
        return "myPage/addPet";
    }
    
    @GetMapping("/myPage")
    public String myPage(HttpServletRequest req, Model model) {
//        model.addAttribute("userInfo", userService.findUserInfoByUserNumber((Long)req.getSession().getAttribute("userNumber")));
        model.addAttribute("userInfo", userService.findUserInfoByUserNumber(2L));
        return "myPage/myPage";
    }

    @PostMapping("/myPage")
    public RedirectView goMypage(String userPassword, HttpServletRequest req, RedirectAttributes redirectAttributes){
        try {
//            if(userPassword == userService.findUserPasswordByUserNumber((Long)req.getSession().getAttribute("userNumber"))){
            if(userPassword.equals(userService.findUserPasswordByUserNumber(2L))){
                return new RedirectView("/myPage/myPage");
            }
        } catch (IllegalArgumentException e) {
            return new RedirectView("/myPage/checkPw");
        }
        return new RedirectView("/myPage/checkPw");
    }

    @PostMapping("/edit")
    public RedirectView editUserInfo(UserDto userDto, HttpServletRequest req){
//        userDto.setUserNumber((Long)req.getSession().getAttribute("userNumber"));
        userDto.setUserNumber(2L);
        userService.editUserInfo(userDto);
        return new RedirectView("/myPage/myPage");
    }

    @GetMapping("/myPet")
    public String myPet() { return "myPage/myPet"; }

    @GetMapping("/checkPw")
    public String checkPw() { return "myPage/checkPw"; }

    @GetMapping("/checkReservation")
    public String checkReservation() { return "myPage/reservation-sitter"; }

    @GetMapping("/reservationList")
    public String reservationList() { return "myPage/reservation-user"; }

    @GetMapping("/strollList")
    public String strollList() { return "myPage/strollList"; }
}
