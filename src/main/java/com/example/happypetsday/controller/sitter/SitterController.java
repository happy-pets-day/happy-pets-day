package com.example.happypetsday.controller.sitter;

import com.example.happypetsday.dto.SitterApplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sitter/*")
public class SitterController {

    @GetMapping("/apply")
    public String sitterApplyTo(){

        return "sitter/applyTo";
    }

    @GetMapping("/addList")
    public String sitterAddList(){
        return "sitter/sitterAddList";
    }

    @GetMapping("/list")
    public String sitterList(){
        return "sitter/sitterList";
    }

    @GetMapping("/profile")
    public String sitterProfile(){
        return "sitter/sitterProfile";
    }

}
