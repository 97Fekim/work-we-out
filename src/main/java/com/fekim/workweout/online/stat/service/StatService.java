package com.fekim.workweout.online.stat.service;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
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
     * 07. 그룹 N주간 멤버별운동일수 조회
     *  - IN = [ 그룹ID, 이번주(YYYY/MM/W) ]
     *  - OUT = [ 회원:운동일수 의 DTO 리스트]
     * */
    MbrWkoutDaysCntsDTO getWeeklyGrpWkoutDaysCnt(Long grpId, String yyyyMmW);

    /**
     * 08. 그룹 N월간 멤버별운동일수 조회
     *  - IN = [ 그룹ID, 이번월(YYYY/MM) ]
     *  - OUT = [ 회원:운동일수 의 DTO 리스트]
     * */
    MbrWkoutDaysCntsDTO getMonthlyGrpWkoutDaysCnt(Long grpId, String yyyyMm);

    /**
     * 09. 그룹 N주간 운동부위별 총 세트수 조회
     *  - IN = [ 그룹ID, 이번주(YYYY/MM/W) ]
     *  - OUT = [ 부위:총세트수 의 DTO 리스트]
     * */
    TargetPartTotalSetsDTO getWeeklyGrpTargetPartTotalSets(Long grpId, String yyyyMmW);

    /**
     * 10. 그룹 N월간 운동부위별 총 세트수 조회
     *  - IN = [ 그룹ID, 이번월(YYYY/MM) ]
     *  - OUT = [ 부위:총세트수 의 DTO 리스트]
     * */
    TargetPartTotalSetsDTO getMonthlyGrpTargetPartTotalSets(Long grpId, String yyyyMm);

    /**
     * 11. 그룹내 멤버별 N주간 운동부위별 총 세트수 조회
     *  - IN = [ 그룹ID, 이번주(YYYY/MM/W) ]
     *  - OUT = [ 회원:[운동부위:총세트수] 의 DTO 리스트]
     * */
    GrpMbrTargetPartTotalSetsDTO getWeeklyGrpMbrTargetPartTotalSets(Long grpId, String yyyyMmW);

    /**
     * 12. 그룹내 멤버별 N월간 운동부위별 총 세트수 조회
     *  - IN = [ 그룹ID, 이번월(YYYY/MM) ]
     *  - OUT = [ 회원:[운동부위:총세트수] 의 DTO 리스트]
     * */
    GrpMbrTargetPartTotalSetsDTO getMonthlyGrpMbrTargetPartTotalSets(Long grpId, String yyyyMm);

    /**
     * 13. 주간 문자발송처리현황조회
     *  - IN = [ YYYY/MM/W ]
     *  - OUT = [ 성공건수/실패건수 DTO ]
     * */
    SmsSendSuccessFailCntDTO getWeeklySmsSendSuccessFailCnt(String yyyyMmW);

    /**
     * 14. 월간 문자발송처리현황조회
     *  - IN = [ YYYY/MM ]
     *  - OUT = [ 성공건수/실패건수 DTO ]
     * */
    SmsSendSuccessFailCntDTO getMonthlySmsSendSuccessFailCnt(String yyyyMm);
    

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

    /**
     * Transform [String]YYYY/MM/W to [YyyyMmW]YYYY/MM/W
     *  - IN = [String] YYYY/MM/W
     *  - OUT = [YyyyMmW]YYYY/MM/W
     * */
    default YyyyMmW makeYyyyMmW(String yyyyMmW) {
        String curYyyy = yyyyMmW.substring(0, 4);
        String curMm = yyyyMmW.substring(4,6);
        String curWeek = yyyyMmW.substring(6,7);

        return YyyyMmW
                .builder()
                .cuofYyyy(curYyyy)
                .cuofMm(curMm)
                .cuofWeek(curWeek)
                .build();
    }

    /**
     * Transform [String]YYYY/MM/W to [YyyyMmW]YYYY/MM/W
     *  - IN = [String] YYYY/MM/W
     *  - OUT = [YyyyMmW]YYYY/MM/W
     * */
    default YyyyMm makeYyyyMm(String yyyyMm) {
        String curYyyy = yyyyMm.substring(0, 4);
        String curMm = yyyyMm.substring(4,6);

        return YyyyMm
                .builder()
                .cuofYyyy(curYyyy)
                .cuofMm(curMm)
                .build();
    }


}

