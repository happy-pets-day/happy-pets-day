package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StrollBoardDto {
    private Long strollBoardNumber;
    private String strollBoardTitle;
    private String strollBoardContent;
    private double strollBoardLat;
    private double strollBoardLng;
    private String strollBoardMeetingDate;
    private String strollBoardMeetingPlace;
    private String strollBoardAdminDistrict;
    private String strollBoardRegisterDate;
    private String strollBoardUpdateDate;
    private Long userNumber;
    private Long petNumber;
    private Long strollBoardViewCount;
}
