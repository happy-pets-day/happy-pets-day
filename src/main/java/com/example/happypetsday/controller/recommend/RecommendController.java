package com.example.happypetsday.controller.recommend;

import com.example.happypetsday.dto.RecommendDto;
import com.example.happypetsday.service.recommend.RecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/recommend/*")
@RequiredArgsConstructor
@Slf4j
public class RecommendController {
    private final RecommendService recommendService;

    @GetMapping("/main")
    public String recommend(){
        return "recommend/petRecommend";
    }

    @GetMapping("/detail")
    public String recommendDetail(@Param("recommendNumber")Long recommendNumber, Model model){
        model.addAttribute("detail", recommendService.findRecommendDetail(recommendNumber));
        return "recommend/petRecommendDetail";
    }

    @GetMapping("/list")
    public String recommendList(){
        return "recommend/petRecommendList";
    }

    @PostMapping("/list")
    public RedirectView recommendList(RecommendDto recommendDto, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("recommendList", recommendService.findRecommendList(recommendDto));
        return new RedirectView("/recommend/list");
    }



}
