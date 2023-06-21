package com.example.happypetsday.controller.stroll;

import com.example.happypetsday.dto.PetDto;
import com.example.happypetsday.dto.StrollBoardDto;
import com.example.happypetsday.service.pet.PetService;
import com.example.happypetsday.service.stroll.StrollService;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.PageVo;
import com.example.happypetsday.vo.StrollBoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/stroll/*")
@RequiredArgsConstructor
public class StrollController {
    private final PetService petService;
    private final StrollService strollService;


    @GetMapping("/view")
    public String boardView(Long strollBoardNumber,Model model,HttpServletRequest req){
        strollService.modifyViewCount(strollBoardNumber);

        StrollBoardVo strollBoard = strollService.findBoard(strollBoardNumber);
        model.addAttribute("board", strollBoard);
        return "strollBoard/strollBoardView";
    }

//  산책 게시글 작성 화면 이동
    @GetMapping("/write")
    public String boardWrite(HttpServletRequest req, Model model){
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");

        List<PetDto> petList = petService.findPet(userNumber);

//      산책할 수 있는 반려동물이 없으면 등록페이지로 이동시키기
        if(petList.size()<1){
            String msg = "먼저 반려동물을 등록해주세요!";
            model.addAttribute("guideMsg",msg);
            return "myPage/addPet";
        }
        model.addAttribute("petList", petList);
        return "strollBoard/strollBoardWrite";
    }

//    산책 게시글 작성
    @PostMapping("/write")
    public RedirectView boardWrite(StrollBoardDto strollBoardDto,HttpServletRequest req){
        strollBoardDto.setUserNumber((Long)req.getSession().getAttribute("userNumber"));
        strollService.register(strollBoardDto);

        return new RedirectView("/stroll/list");
    }


    @GetMapping("/list")
    public String strollBoardList(Criteria criteria, Model model){
        List<StrollBoardVo> boardList = strollService.findAll(criteria);

        model.addAttribute("boardList",boardList);
        model.addAttribute("pageInfo", new PageVo(criteria, strollService.getTotal()));
        return "strollBoard/strollBoardList";
    }
}
