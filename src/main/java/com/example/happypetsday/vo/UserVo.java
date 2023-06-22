package com.example.happypetsday.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserVo {
    private Long userNumber;
    private String userId;
    private String userPassword;
    private String userName;
    private String userGender;
    private String userEmail;
    private String userBirth;
    private String userPhoneNumber;
    private String userAddress;
    private int questionNumber;
    private String findPasswordAnswer;
    private int userStatus;
    private String userStatusResult;
}
