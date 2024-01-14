package com.fekim.workweout.online.stat.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MbrWkoutDaysCntDTO {

    Long mbrId;
    String mbrNm;
    String profImgPath;
    Long wkoutDaysCnt;

}
