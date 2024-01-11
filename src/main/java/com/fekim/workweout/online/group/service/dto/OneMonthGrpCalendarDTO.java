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
public class OneMonthGrpCalendarDTO {

    private Long grpId;

    @Builder.Default
    private List<OneDayGrpJnalsDTO> oneDayGrpJnalsDTOList = new ArrayList<>();

}
