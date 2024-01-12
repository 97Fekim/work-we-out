package com.fekim.workweout.online.stat.service;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.stat.repository.StatRepository;
import com.fekim.workweout.online.stat.service.dto.MethodWeiIncsDTO;
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

    /**
     * 03. 회원 주간 운동종목별 중량증감 조회
     *  - IN = [ 회원ID, YYYY/MM/W ]
     *  - OUT = [ 운동종목:무게증감 의 DTO 리스트]
     * */
    @Override
    public MethodWeiIncsDTO getWeeklyMethodWeiIncs(Long mbrId, String bfYyyyMmW, String curYyyyMmW) {

        String bfYyyy = bfYyyyMmW.substring(0, 4);
        String bfMm = bfYyyyMmW.substring(4,6);
        String bfWeek = bfYyyyMmW.substring(6,7);

        String curYyyy = curYyyyMmW.substring(0, 4);
        String curMm = curYyyyMmW.substring(4,6);
        String curWeek = curYyyyMmW.substring(6,7);

        List<Object[]> entities = statRepository.findWeeklyMethodWeiIncs(
                mbrId,
                YyyyMmW.builder()
                        .cuofYyyy(bfYyyy)
                        .cuofMm(bfMm)
                        .cuofWeek(bfWeek)
                        .build(),
                YyyyMmW.builder()
                        .cuofYyyy(curYyyy)
                        .cuofMm(curMm)
                        .cuofWeek(curWeek)
                        .build()
        );

        return makeMethodWeiIncsDTO(entities);
    }

    /**
     * 04. 회원 월간 운동종목별 중량증감 조회
     *  - IN = [ 회원ID, 전월(YYYY/MM), 이번월(YYYY/MM) ]
     *  - OUT = [ 운동종목:무게증감 의 DTO 리스트]
     * */
    @Override
    public MethodWeiIncsDTO getMonthlyMethodWeiIncs(Long mbrId, String bfYyyyMm, String curYyyyMm) {

        String bfYyyy = bfYyyyMm.substring(0, 4);
        String bfMm = bfYyyyMm.substring(4,6);

        String curYyyy = curYyyyMm.substring(0, 4);
        String curMm = curYyyyMm.substring(4,6);

        List<Object[]> entities = statRepository.findMonthlyMethodWeiIncs(
                mbrId,
                YyyyMm.builder().cuofYyyy(bfYyyy).cuofMm(bfMm).build(),
                YyyyMm.builder().cuofYyyy(curYyyy).cuofMm(curMm).build()
        );

        return makeMethodWeiIncsDTO(entities);
    }


}
