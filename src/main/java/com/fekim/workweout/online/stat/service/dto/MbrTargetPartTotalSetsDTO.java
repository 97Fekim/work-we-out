package com.fekim.workweout.online.stat.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MbrTargetPartTotalSetsDTO {

    private Long mbrId;
    private String mbrNm;
    private String profImgPath;
    private TargetPartTotalSetsDTO targetPartTotalSetsDTO;
}
