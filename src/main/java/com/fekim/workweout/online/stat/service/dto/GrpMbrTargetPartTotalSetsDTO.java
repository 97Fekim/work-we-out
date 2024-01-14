package com.fekim.workweout.online.stat.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrpMbrTargetPartTotalSetsDTO {

    @Builder.Default
    List<MbrTargetPartTotalSetsDTO> mbrTargetPartTotalSetsDTOList = new ArrayList<>();
}
