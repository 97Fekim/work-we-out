package com.fekim.workweout.online.stat.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeekMaxWeiDTO {

    String cuofYyyy;
    String coufMm;
    String cuofWeek;
    Long maxWeight;

}
