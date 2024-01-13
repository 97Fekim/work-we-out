package com.fekim.workweout.online.stat.service;

import com.fekim.workweout.online.date.repository.DateRepository;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.jnal.repository.WkoutMethodRepository;
import com.fekim.workweout.online.stat.repository.StatRepository;
import com.fekim.workweout.online.stat.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatServiceImpl implements StatService{

    private final StatRepository statRepository;
    private final DateRepository dateRepository;
    private final WkoutMethodRepository wkoutMethodRepository;

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

    /**
     * 05. 회원 N주간 운동종목별 중량추이 조회
     *  - IN = [ 회원ID, 이번주(YYYY/MM/W), 계산범위 ]
     *  - OUT = [ 운동종목:계산범위주별무게 의 DTO 리스트]
     * */
    @Override
    public MethodWeekMaxWeisDTO getMethodWeekMaxWeis(Long mbrId, String yyyyMmW, int range) {

        if (range < 1) {
            log.error("[ERROR]===========================================");
            log.error("[ERROR]Week range is less than 1.");
            log.error("[ERROR]===========================================");
        }

        /* (1) 가장 최신의 Week 를 구한다. */
        String curYyyy = yyyyMmW.substring(0, 4);
        String curMm = yyyyMmW.substring(4,6);
        String curWeek = yyyyMmW.substring(6,7);
        YyyyMmW curYyyyMmW = YyyyMmW
                .builder()
                .cuofYyyy(curYyyy)
                .cuofMm(curMm)
                .cuofWeek(curWeek)
                .build();


        /* (2)
        * - (2-1) [-N번째Week ~ 가장최신Week] 의 Week 정보를 리스트로 구성한다.
        * - (2-2) [-N번째Week ~ 가장최신Week] 까지 존재하는 모든 운동종목ID 중복제거하여 구성한다.
        *  */
        Set<Long> methodIdSet = new TreeSet<>();
        List<YyyyMmW> allWeeks = new ArrayList<>();

        for (long i=range-1; i>=0; i--) {  // 중복제거를 위해 Set으로 1차구성
            List<Object[]> bfYyyyMmWEntity = dateRepository.findBeforeCuofYyyyMmW(curYyyyMmW, i);

            YyyyMmW bfYyyyMmW = YyyyMmW
                    .builder()
                    .cuofYyyy(String.valueOf(bfYyyyMmWEntity.get(0)[0]))
                    .cuofMm(String.valueOf(bfYyyyMmWEntity.get(0)[1]))
                    .cuofWeek(String.valueOf(bfYyyyMmWEntity.get(0)[2]))
                    .build();

            // (2-1) Week 정보 리스트 구성
            allWeeks.add(bfYyyyMmW);

            // (2-2) 운동종목ID Set 구성
            List<Long> methodIds = statRepository.findAllMethodInWeek(
                    mbrId,
                    bfYyyyMmW
            );

            methodIdSet.addAll(methodIds);
        }

        System.out.println("[DEBUG]===========================================");
        System.out.println("[DEBUG]METHODS INFO = " + methodIdSet.toString());
        for (YyyyMmW ymw : allWeeks) {
            System.out.println("[DEBUG]WEEKS INFO = " + ymw.getCuofYyyy()+"/"+ymw.getCuofMm()+"/"+ymw.getCuofWeek());        }
        System.out.println("[DEBUG]===========================================");


        /* (3) 최종DTO생성 = 운동종목ID셋 X Weeks리스트 */
        MethodWeekMaxWeisDTO methodWeekMaxWeisDTO =
                MethodWeekMaxWeisDTO.builder().build();

        for (Long methodId : methodIdSet) {

            List<WeekMaxWeiDTO> weekMaxWeiDTOs = new ArrayList<>();
            for (YyyyMmW week : allWeeks) {

                Long maxWeight = statRepository.findMethodMaxWeiInWeek(
                        mbrId, methodId, week
                );

                weekMaxWeiDTOs.add(WeekMaxWeiDTO
                        .builder()
                        .cuofYyyy(week.getCuofYyyy())
                        .coufMm(week.getCuofMm())
                        .cuofWeek(week.getCuofWeek())
                        .maxWeight(maxWeight)
                        .build()
                );
            }

            methodWeekMaxWeisDTO.getMethodWeekMaxWeiDTOList().add(
                    MethodWeekMaxWeiDTO
                            .builder()
                            .methodId(methodId)
                            .methodNm(wkoutMethodRepository.findById(methodId).get().getMethodNm())
                            .weekMaxWeiDTOList(weekMaxWeiDTOs)
                            .build()
            );
        }

        return methodWeekMaxWeisDTO;

    }


}
