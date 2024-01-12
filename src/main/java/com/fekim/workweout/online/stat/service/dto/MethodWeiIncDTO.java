package com.fekim.workweout.online.stat.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MethodWeiIncDTO {

    private Long methodId;
    private String methodNm;
    private Long bfMonthMaxWei;
    private Long curMonthMaxWei;
    private Long weiIcrease;

}
