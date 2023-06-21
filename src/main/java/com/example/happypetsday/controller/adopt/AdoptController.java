package com.example.happypetsday.controller.adopt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adopt/*")
public class AdoptController {
    @GetMapping("/list")
    public String AdoptionInformation(){
        return "AdoptionInformation/AdoptionInformation";
    }
    @GetMapping("/detail")
    public String detail(){return "AdoptionInformation/adoptDetail";}
}
