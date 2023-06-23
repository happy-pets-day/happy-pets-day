package com.example.happypetsday.controller.pet;

import com.example.happypetsday.service.pet.PetFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/petFile/*")
public class PetFileController {
    private final PetFileService petFileService;

    @Value("${petFile.dir}")
    private String fileDir;



}
