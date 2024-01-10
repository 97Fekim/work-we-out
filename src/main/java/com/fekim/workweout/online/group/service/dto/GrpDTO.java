package com.fekim.workweout.online.group.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrpDTO {

    private Long grpId;   // 그룹ID
    private String grpNm; // 그룹명
    private String srtDt; // 그룹시작일자

    @Builder.Default
    List<MemberGrpDTO> memberGrpDTOList = new ArrayList<>();

}
