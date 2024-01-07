package com.fekim.workweout.online.jnal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WkoutJnalMethodDTO {

    private String methodNm;    // 운동종목명
    private Long weight;        // 무게
    private Long sets;          // 세트수
    private Long reps;          // 랩수
    private Long restTime;      // 휴식시간초

}
