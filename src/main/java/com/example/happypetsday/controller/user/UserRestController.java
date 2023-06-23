package com.example.happypetsday.controller.user;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/*")
public class UserRestController {
    private final UserService userService;

    @GetMapping("/checkDuplicate")
    public boolean checkDuplicate(@RequestParam String userId) {
        return userService.isUserIdAvailable(userId);
    }
}

