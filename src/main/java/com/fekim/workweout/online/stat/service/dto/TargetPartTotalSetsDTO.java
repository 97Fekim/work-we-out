package com.fekim.workweout.online.stat.service.dto;


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
public class TargetPartTotalSetsDTO {

    @Builder.Default
    List<TargetPartTotalSetDTO> targetPartTotalSetDTOList = new ArrayList<>();

}
