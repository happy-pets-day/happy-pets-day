package com.example.happypetsday.controller.sitter;

import com.example.happypetsday.dto.SitterApplyDto;
import com.example.happypetsday.service.sitter.SitterApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sitter/*")
public class SitterController {
    private final SitterApplyService sitterApplyService;

    @GetMapping("/apply")
    public String sitterApplyTo(){

        return "sitter/applyTo";
    }

    @PostMapping("/apply")
    public RedirectView sendApply( SitterApplyDto sitterApplyDto, HttpServletRequest req){
//        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
//        sitterApplyDto.setUserNumber(userNumber);
        sitterApplyDto.setUserNumber(3L);
        sitterApplyService.register(sitterApplyDto);
//        if(files != null){
//            try {
//                fileService.registerAndSaveFiles(files, boardDto.getBoardNumber());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return new RedirectView("/list");
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
