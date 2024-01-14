package com.fekim.workweout.online.stat.service;

import com.fekim.workweout.online.stat.service.dto.*;

import java.math.BigDecimal;
import java.util.List;

public interface StatService {

    /**
     * 01. 회원 주간 운동부위별 총세트수 조회
     *  - IN = [ 회원ID, YYYY/MM/W ]
     *  - OUT = [ 부위:총세트수 의 DTO 리스트 ]
     * */
    TargetPartTotalSetsDTO getWeeklyTargetPartTotalSets(Long mbrId, String yyyyMmW);

    /**
     * 02. 회원 월간 운동부위별 총세트수 조회
     *  - IN = [ 회원ID, YYYY/MM ]
     *  - OUT = [ 부위:총세트수 의 DTO 리스트 ]
     * */
    TargetPartTotalSetsDTO getMonthlyTargetPartTotalSets(Long mbrId, String yyyyMm);

    /**
     * 03. 회원 주간 운동종목별 중량증감 조회
     *  - IN = [ 회원ID, 전주(YYYY/MM/DD), 이번주(YYYY/MM/DD) ]
     *  - OUT = [ 운동종목:무게증감 의 DTO 리스트]
     * */
    MethodWeiIncsDTO getWeeklyMethodWeiIncs(Long mbrId, String bfYyyyMmW, String curYyyyMmW);

    /**
     * 04. 회원 월간 운동종목별 중량증감 조회
     *  - IN = [ 회원ID, 전월(YYYY/MM), 이번월(YYYY/MM) ]
     *  - OUT = [ 운동종목:무게증감 의 DTO 리스트]
     * */
    MethodWeiIncsDTO getMonthlyMethodWeiIncs(Long mbrId, String bfYyyyMm, String curYyyyMm);

    /**
     * 05. 회원 N주간 운동종목별 중량추이 조회
     *  - IN = [ 회원ID, 이번주(YYYY/MM/W), 계산범위 ]
     *  - OUT = [ 운동종목:계산범위주별무게 의 DTO 리스트]
     * */
    MethodWeekMaxWeisDTO getMethodWeekMaxWeis(Long mbrId, String yyyyMmW, int range);

    /**
     * 06. 회원 N월간 운동종목별 중량추이 조회
     *  - IN = [ 회원ID, 이번월(YYYY/MM/W), 계산범위 ]
     *  - OUT = [ 운동종목:계산범위주별무게 의 DTO 리스트]
     * */
    MethodMonthMaxWeisDTO getMethodMonthMaxWeis(Long mbrId, String yyyyMm, int range);


    /**
     * Transform TargetPartTotalSets entity to DTO
     *  - IN = [List] Complex TargetPartTotalSets Entity
     *  - OUT = [List] TargetPartTotalSetsDTO
     * */
    default TargetPartTotalSetsDTO makeTargetPartTotalSetsDTO(List<Object[]> entities) {
        TargetPartTotalSetsDTO targetPartTotalSetsDTO = TargetPartTotalSetsDTO.builder().build();

        for (Object[] entity : entities) {
            targetPartTotalSetsDTO.getTargetPartTotalSetDTOList().add(
                    TargetPartTotalSetDTO.builder()
                            .targetPart((String) entity[0])
                            .totalSets((Long) entity[1])
                            .build()
            );
        }
        return targetPartTotalSetsDTO;
    }

    /**
     * Transform MethodWeightIncrease entity to DTO
     *  - IN = [List] Complex MethodWeightIncrease Entity
     *  - OUT = [List] MethodWeightIncrease DTO
     * */
    default MethodWeiIncsDTO makeMethodWeiIncsDTO(List<Object[]> entities) {
        MethodWeiIncsDTO methodWeiIncsDTO = MethodWeiIncsDTO.builder().build();

        for (Object[] entity : entities) {
            methodWeiIncsDTO.getMethodWeiIncDTOList().add(
                    MethodWeiIncDTO.builder()
                            .methodId(((BigDecimal) entity[0]).longValue())
                            .methodNm(String.valueOf(entity[1]))
                            .bfMonthMaxWei(((BigDecimal) entity[2]).longValue())
                            .curMonthMaxWei(((BigDecimal) entity[3]).longValue())
                            .weiIcrease(((BigDecimal) entity[4]).longValue())
                            .build()
            );
        }
        return methodWeiIncsDTO;
    }

}

