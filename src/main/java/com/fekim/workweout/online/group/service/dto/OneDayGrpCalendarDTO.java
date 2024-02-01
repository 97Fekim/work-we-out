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
public class OneDayGrpCalendarDTO {

    String yyyy;
    String mm;
    String dd;
    String dayOfWeekClsfCd;

    @Builder.Default
    List<MemberGrpDTO> memberGrpDTOList = new ArrayList<>();

}
