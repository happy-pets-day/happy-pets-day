package com.example.happypetsday.controller.sitter;

import com.example.happypetsday.dto.*;
import com.example.happypetsday.service.sitter.*;
import com.example.happypetsday.vo.SitterListVo;
import com.example.happypetsday.vo.SitterVo;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sitter/*")
public class SitterController {
    private final SitterApplyService sitterApplyService;
    private final SitterApplyLicenseFileService sitterApplyLicenseFileService;
    private final SitterListService sitterListService;
    private final SitterProfileFileService sitterProfileFileService;
    private final SitterFileService sitterFileService;
    private final SitterService sitterService;
    private final SitterFieldService sitterFieldService;

    @GetMapping("/apply")
    public String sitterApplyTo(){

        return "sitter/applyTo";
    }

    @PostMapping("/apply")
    public RedirectView sendApply( SitterFieldDto sitterFieldDto,
            SitterApplyDto sitterApplyDto, HttpServletRequest req, RedirectAttributes redirectAttributes,
            @RequestParam("applyFile") List<MultipartFile> files, @RequestParam("applyFileTitle") List<String> applyFileTitle
    ) {
        sitterApplyDto.setUserNumber((Long)req.getSession().getAttribute("userNumber"));
        sitterApplyService.register(sitterApplyDto);

        String[] fieldNames = req.getParameterValues("petFieldName");
        for(String fieldName : fieldNames){
        sitterFieldDto.setPetFieldName(fieldName);
        sitterFieldDto.setUserNumber((Long)req.getSession().getAttribute("userNumber"));
        sitterFieldService.register(sitterFieldDto);
        }


        redirectAttributes.addFlashAttribute("applyNumber", sitterApplyDto.getApplyNumber());

        if (files != null && !files.isEmpty()) { // 파일 리스트가 null이 아니고 비어있지 않을 경우에만 처리
            try {
                sitterApplyLicenseFileService.registerAndSaveFiles(files, sitterApplyDto.getApplyNumber(), applyFileTitle, sitterApplyDto.getUserNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        System.out.println("1" + sitterApplyDto);
//        System.out.println("2" + files);
//        System.out.println("3" + applyFileTitle);
        return new RedirectView("/sitter/apply");
    }


    @GetMapping("/addList")
    public String sitterAddList(){
        return "sitter/sitterAddList";
    }

    @PostMapping("/addList")
    public RedirectView sendAddList(HttpServletRequest req, SitterDto sitterDto,
    @RequestParam("sitterProfileFile") List<MultipartFile> filess, @RequestParam("sitterFile")List<MultipartFile> files){
        sitterDto.setUserNumber((Long)req.getSession().getAttribute("userNumber"));
        long sitterNumber = sitterService.findSitter(sitterDto.getUserNumber());
        sitterDto.setSitterNumber(sitterNumber);

        sitterService.addList(sitterDto);
        try {
            sitterFileService.registerAndSaveFiles(files, sitterDto.getSitterNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sitterProfileFileService.registerAndSaveFiles(filess, sitterDto.getSitterNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new RedirectView("/sitter/list");
    }

    @GetMapping("/list")
    public String sitterList(@RequestParam(required = false) Long sitterNumber, Model model, HttpServletRequest req,
                             List<MultipartFile> files) {


        List<SitterListVo> sitterList = sitterListService.findAll();

        if(req.getSession().getAttribute("userNumber") != null){
            model.addAttribute("showBtn",sitterListService.countSitter((Long)req.getSession().getAttribute("userNumber")));
        }
            model.addAttribute("sitterNumber", sitterNumber);
            model.addAttribute("sitterList", sitterList);

        return "sitter/sitterList";
    }




    @GetMapping("/profile")
    public String sitterProfile(Long sitterNumber, Model model){

        return "sitter/sitterProfile";
    }

    @PostMapping("/profile")
    public RedirectView sitterProfile(Long sitterNumber, HttpServletRequest req){
        return new RedirectView("/profile");
    }


}
