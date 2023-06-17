package com.example.happypetsday.controller.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myPage/*")
public class MyPageController {

    @GetMapping("/addPet")
    public String addPet(){
        return "myPage/addPet";
    }


}
