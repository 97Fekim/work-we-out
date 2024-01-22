package com.fekim.workweout.online.jnal.service.dto;


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
public class OneDayCalendarDTO {

    private String yyyy;        // 년도
    private String mm;          // 월
    private String dd;          // 일
    private String dayOfWeekClsfCd; // 요일

    @Builder.Default
    private List<String> parts = new ArrayList<>(); // 운동부위리스트

}
