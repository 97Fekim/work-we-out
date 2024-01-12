package com.fekim.workweout.online.stat.service;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.stat.repository.StatRepository;
import com.fekim.workweout.online.stat.service.dto.TargetPartTotalSetsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatServiceImpl implements StatService{

    private final StatRepository statRepository;

    /**
     * 01. 회원 주간 운동부위별 총세트수 조회
     *  - IN = [ 회원ID, YYYY/MM/W ]
     *  - OUT = [ 부위:총세트수 의 DTO 리스트 ]
     * */
    @Override
    public TargetPartTotalSetsDTO getWeeklyTargetPartTotalSets(Long mbrId, String yyyyMmW) {

        String yyyy = yyyyMmW.substring(0, 4);
        String mm = yyyyMmW.substring(4,6);
        String week = yyyyMmW.substring(6,7);

        List<Object[]> entities = statRepository.findWeeklyMethodTotalSets(
                mbrId, YyyyMmW
                        .builder()
                        .cuofYyyy(yyyy)
                        .cuofMm(mm)
                        .cuofWeek(week)
                        .build());

        return makeTargetPartTotalSetsDTO(entities);
    }

    /**
     * 02. 회원 월간 운동부위별 총세트수 조회
     *  - IN = [ 회원ID, YYYY/MM ]
     *  - OUT = [ 부위:총세트수 의 DTO 리스트 ]
     * */
    @Override
    public TargetPartTotalSetsDTO getMonthlyTargetPartTotalSets(Long mbrId, String yyyyMm) {

        String yyyy = yyyyMm.substring(0, 4);
        String mm = yyyyMm.substring(4,6);

        List<Object[]> entities = statRepository.findMonthlyMethodTotalSets(
                mbrId, YyyyMm
                        .builder()
                        .cuofYyyy(yyyy)
                        .cuofMm(mm)
                        .build());

        return makeTargetPartTotalSetsDTO(entities);
    }
}
