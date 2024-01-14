package com.fekim.workweout.online.stat.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsSendSuccessFailCntDTO {

    @Builder.Default
    Long successCnt = 0L;
    @Builder.Default
    Long failCnt = 0L;

}
