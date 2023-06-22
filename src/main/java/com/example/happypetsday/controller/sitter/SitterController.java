package com.example.happypetsday.controller.sitter;

import com.example.happypetsday.dto.SitterApplyDto;
import com.example.happypetsday.dto.SitterApplyLicenseFile;
import com.example.happypetsday.service.sitter.SitterApplyLicenseFileService;
import com.example.happypetsday.service.sitter.SitterApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sitter/*")
public class SitterController {
    private final SitterApplyService sitterApplyService;
    private final SitterApplyLicenseFileService sitterApplyLicenseFileService;

    @GetMapping("/apply")
    public String sitterApplyTo(){

        return "sitter/applyTo";
    }

    @PostMapping("/apply")
    public RedirectView sendApply( SitterApplyDto sitterApplyDto, HttpServletRequest req,
                                   RedirectAttributes redirectAttributes,@RequestParam("applyFile") List<MultipartFile> files,
                                   @RequestParam("applyFileTitle")List<String> applyFileTitle, SitterApplyLicenseFile sitterApplyLicenseFile){

//        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
//        sitterApplyDto.setUserNumber(userNumber);

        sitterApplyDto.setUserNumber(3L);
        sitterApplyLicenseFile.setUserNumber(3L);
//        System.out.println(applyFileTitle);
//        System.out.println(sitterApplyDto);

        sitterApplyService.register(sitterApplyDto);

        redirectAttributes.addFlashAttribute("applyNumber", sitterApplyDto.getApplyNumber());


        if(files != null){
            try {
                sitterApplyLicenseFileService.registerAndSaveFiles(
                        files, sitterApplyDto.getApplyNumber(), applyFileTitle, sitterApplyDto.getUserNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new RedirectView("/sitter/apply");
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
