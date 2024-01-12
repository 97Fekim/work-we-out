package com.fekim.workweout.online.stat.service;

import com.fekim.workweout.online.stat.service.dto.TargetPartTotalSetDTO;
import com.fekim.workweout.online.stat.service.dto.TargetPartTotalSetsDTO;

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

}

