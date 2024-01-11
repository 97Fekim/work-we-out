package com.fekim.workweout.online.stat.service;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.stat.service.dto.TargetPartTotalSetsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatServiceImpl implements StatService{

    /**
     * 01. 회원 주간 운동부위별 총세트수 조회
     *  - IN = [ 회원ID, YYYY/MM/W ]
     *  - OUT = [ 부위:총세트수 의 DTO 리스트 ]
     * */
    @Override
    public TargetPartTotalSetsDTO getWeeklyTargetPartTotalSets(Long id, YyyyMm yyyyMm) {
        return null;
    }

    /**
     * 02. 회원 월간 운동부위별 총세트수 조회
     *  - IN = [ 회원ID, YYYY/MM ]
     *  - OUT = [ 부위:총세트수 의 DTO 리스트 ]
     * */
    @Override
    public TargetPartTotalSetsDTO getMonthlyTargetPartTotalSets(Long id, YyyyMm yyyyMm) {
        return null;
    }
}
