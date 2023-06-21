package com.example.happypetsday.controller.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/myPage/*")
public class MyPageController {

    @GetMapping("/addPet")
    public String addPet(){
        return "myPage/addPet";
    }

    @GetMapping("/main")
    public String myPage() { return "myPage/myPage"; }

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
