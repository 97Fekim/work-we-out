package com.fekim.workweout.online.jnal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WkoutMethodDTO {

    private Long methodId;      // 운동종목ID
    private String methodNm;    // 운동종목명
    private String targetPart;  // 타겟부위

}
