package com.example.happypetsday.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StrollBoardVo {
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
    private String userId;
    private String petName;
    private String petBirth;
    private String petKind;
    private String petDetailedType;
    private String petGender;
    private String petSurgery;
    private String petFileName;
    private String petFileUploadPath;
    private String petFileUuid;
}
