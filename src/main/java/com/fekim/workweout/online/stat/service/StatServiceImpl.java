package com.fekim.workweout.online.stat.service;

import com.fekim.workweout.online.date.repository.DateRepository;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.group.repository.MemberGrpRepository;
import com.fekim.workweout.online.jnal.repository.WkoutMethodRepository;
import com.fekim.workweout.online.member.repository.MemberRepository;
import com.fekim.workweout.online.member.repository.entity.Member;
import com.fekim.workweout.online.stat.repository.*;
import com.fekim.workweout.online.stat.repository.entity.MonthlyWkoutStatSchedule;
import com.fekim.workweout.online.stat.repository.entity.WeeklyWkoutStatSchedule;
import com.fekim.workweout.online.stat.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatServiceImpl implements StatService{

    private final MemberRepository memberRepository;
    private final MemberGrpRepository memberGrpRepository;
    private final StatRepository statRepository;
    private final DateRepository dateRepository;
    private final WkoutMethodRepository wkoutMethodRepository;
    private final WeeklyWkoutStatScheduleRepository weeklyWkoutStatScheduleRepository;
    private final WeeklyWkoutStatRsltRepository weeklyWkoutStatRsltRepository;
    private final MonthlyWkoutStatScheduleRepository monthlyWkoutStatScheduleRepository;
    private final MonthlyWkoutStatRsltRepository monthlyWkoutStatRsltRepository;

    /**
     * 01. 회원 주간 운동부위별 총세트수 조회
     *  - IN = [ 회원ID, YYYY/MM/W ]
     *  - OUT = [ 부위:총세트수 의 DTO 리스트 ]
     * */
    @Override
    public TargetPartTotalSetsDTO getWeeklyTargetPartTotalSets(Long mbrId, String yyyyMmW) {

        List<Object[]> entities = statRepository.findWeeklyMethodTotalSets(
                mbrId, makeYyyyMmW(yyyyMmW));

        return makeTargetPartTotalSetsDTO(entities);
    }

    /**
     * 02. 회원 월간 운동부위별 총세트수 조회
     *  - IN = [ 회원ID, YYYY/MM ]
     *  - OUT = [ 부위:총세트수 의 DTO 리스트 ]
     * */
    @Override
    public TargetPartTotalSetsDTO getMonthlyTargetPartTotalSets(Long mbrId, String yyyyMm) {

        List<Object[]> entities = statRepository.findMonthlyMethodTotalSets(
                mbrId, makeYyyyMm(yyyyMm));

        return makeTargetPartTotalSetsDTO(entities);
    }

    /**
     * 03. 회원 주간 운동종목별 중량증감 조회
     *  - IN = [ 회원ID, YYYY/MM/W ]
     *  - OUT = [ 운동종목:무게증감 의 DTO 리스트]
     * */
    @Override
    public MethodWeiIncsDTO getWeeklyMethodWeiIncs(Long mbrId, String bfYyyyMmW, String curYyyyMmW) {

        List<Object[]> entities = statRepository.findWeeklyMethodWeiIncs(
                mbrId,
                makeYyyyMmW(bfYyyyMmW),
                makeYyyyMmW(curYyyyMmW)
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

        List<Object[]> entities = statRepository.findMonthlyMethodWeiIncs(
                mbrId,
                makeYyyyMm(bfYyyyMm),
                makeYyyyMm(curYyyyMm)
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
        YyyyMmW curYyyyMmW = makeYyyyMmW(yyyyMmW);


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

    /**
     * 06. 회원 N월간 운동종목별 중량추이 조회
     *  - IN = [ 회원ID, 이번월(YYYY/MM/W), 계산범위 ]
     *  - OUT = [ 운동종목:계산범위주별무게 의 DTO 리스트]
     * */
    @Override
    public MethodMonthMaxWeisDTO getMethodMonthMaxWeis(Long mbrId, String yyyyMm, int range) {

        if (range < 1) {
            log.error("[ERROR]===========================================");
            log.error("[ERROR]Month range is less than 1.");
            log.error("[ERROR]===========================================");
        }

        /* (1) 가장 최신의 Month 를 구한다. */
        YyyyMm curYyyyMm = makeYyyyMm(yyyyMm);

        /* (2)
         * - (2-1) [-N번째Month ~ 가장최신Month] 의 Month 정보를 리스트로 구성한다.
         * - (2-2) [-N번째Month ~ 가장최신Month] 까지 존재하는 모든 운동종목ID 중복제거하여 구성한다.
         *  */
        Set<Long> methodIdSet = new TreeSet<>();
        List<YyyyMm> allMonths = new ArrayList<>();

        for (long i=range-1; i>=0; i--) {  // 중복제거를 위해 Set으로 1차구성
            List<Object[]> bfYyyyMmWEntity = dateRepository.findBeforeCuofYyyyMm(curYyyyMm, i);

            YyyyMm bfYyyyMm = YyyyMm
                    .builder()
                    .cuofYyyy(String.valueOf(bfYyyyMmWEntity.get(0)[0]))
                    .cuofMm(String.valueOf(bfYyyyMmWEntity.get(0)[1]))
                    .build();

            // (2-1) Month 정보 리스트 구성
            allMonths.add(bfYyyyMm);

            // (2-2) 운동종목ID Set 구성
            List<Long> methodIds = statRepository.findAllMethodInMonth(
                    mbrId,
                    bfYyyyMm
            );

            methodIdSet.addAll(methodIds);
        }

        System.out.println("[DEBUG]===========================================");
        System.out.println("[DEBUG]METHODS INFO = " + methodIdSet.toString());
        for (YyyyMm ym : allMonths) {
            System.out.println("[DEBUG]MONTHS INFO = " + ym.getCuofYyyy()+"/"+ym.getCuofMm());
        }
        System.out.println("[DEBUG]===========================================");


        /* (3) 최종DTO생성 = 운동종목ID셋 X Months리스트 */
        MethodMonthMaxWeisDTO methodMonthMaxWeisDTO =
                MethodMonthMaxWeisDTO.builder().build();

        for (Long methodId : methodIdSet) {

            List<MonthMaxWeiDTO> monthMaxWeiDTOs = new ArrayList<>();
            for (YyyyMm month : allMonths) {

                Long maxWeight = statRepository.findMethodMaxWeiInMonth(
                        mbrId, methodId, month
                );

                monthMaxWeiDTOs.add(MonthMaxWeiDTO
                        .builder()
                        .cuofYyyy(month.getCuofYyyy())
                        .coufMm(month.getCuofMm())
                        .maxWeight(maxWeight)
                        .build()
                );
            }

            methodMonthMaxWeisDTO.getMethodMonthMaxWeiDTOList().add(
                    MethodMonthMaxWeiDTO
                            .builder()
                            .methodId(methodId)
                            .methodNm(wkoutMethodRepository.findById(methodId).get().getMethodNm())
                            .monthMaxWeiDTOList(monthMaxWeiDTOs)
                            .build()
            );
        }

        return methodMonthMaxWeisDTO;
    }

    /**
     * 06. 그룹 N주간 멤버별운동일수 조회
     *  - IN = [ 그룹ID, 이번주(YYYY/MM/W) ]
     *  - OUT = [ 회원:운동일수 의 DTO 리스트]
     * */
    @Override
    public MbrWkoutDaysCntsDTO getWeeklyGrpWkoutDaysCnt(Long grpId, String yyyyMmW) {

        YyyyMmW curYyyyMmW = makeYyyyMmW(yyyyMmW);

        List<Object[]> weekGrpMemberTotalWkoutDaysCnt = statRepository.findWeekGrpMemberTotalWkoutDaysCnt(grpId, curYyyyMmW);

        MbrWkoutDaysCntsDTO mbrWkoutDaysCntsDTO = MbrWkoutDaysCntsDTO.builder().build();

        for (Object[] eneity : weekGrpMemberTotalWkoutDaysCnt) {

            Member mbrInfo = memberRepository.findById((Long)eneity[0]).get();

            Long mbrId = mbrInfo.getMbrId();
            String mbrNm = mbrInfo.getMbrNm();
            String profImgPath = mbrInfo.getProfImgPath();
            Long wkoutDaysCnt = (Long)eneity[4];

            mbrWkoutDaysCntsDTO.getMbrWkoutDaysCntDTOList().add(
                    MbrWkoutDaysCntDTO.builder()
                            .mbrId(mbrId)
                            .mbrNm(mbrNm)
                            .profImgPath(profImgPath)
                            .wkoutDaysCnt(wkoutDaysCnt)
                            .build()
            );

        }

        return mbrWkoutDaysCntsDTO;
    }

    /**
     * 08. 그룹 N월간 멤버별운동일수 조회
     *  - IN = [ 그룹ID, 이번월(YYYY/MM) ]
     *  - OUT = [ 회원:운동일수 의 DTO 리스트]
     * */
    @Override
    public MbrWkoutDaysCntsDTO getMonthlyGrpWkoutDaysCnt(Long grpId, String yyyyMm) {

        YyyyMm curYyyyMm = makeYyyyMm(yyyyMm);

        List<Object[]> monthGrpMemberTotalWkoutDaysCnt = statRepository.findMonthGrpMemberTotalWkoutDaysCnt(grpId, curYyyyMm);

        MbrWkoutDaysCntsDTO mbrWkoutDaysCntsDTO = MbrWkoutDaysCntsDTO.builder().build();

        for (Object[] eneity : monthGrpMemberTotalWkoutDaysCnt) {

            Member mbrInfo = memberRepository.findById((Long)eneity[0]).get();

            Long mbrId = mbrInfo.getMbrId();
            String mbrNm = mbrInfo.getMbrNm();
            String profImgPath = mbrInfo.getProfImgPath();
            Long wkoutDaysCnt = (Long)eneity[3];

            mbrWkoutDaysCntsDTO.getMbrWkoutDaysCntDTOList().add(
                    MbrWkoutDaysCntDTO.builder()
                            .mbrId(mbrId)
                            .mbrNm(mbrNm)
                            .profImgPath(profImgPath)
                            .wkoutDaysCnt(wkoutDaysCnt)
                            .build()
            );

        }

        return mbrWkoutDaysCntsDTO;
    }

    /**
     * 09. 그룹 N주간 운동부위별 총 세트수 조회
     *  - IN = [ 그룹ID, 이번주(YYYY/MM/W) ]
     *  - OUT = [ 부위:총세트수 의 DTO 리스트]
     * */
    @Override
    public TargetPartTotalSetsDTO getWeeklyGrpTargetPartTotalSets(Long grpId, String yyyyMmW) {
        List<Object[]> entities = statRepository.findWeekGrpTargetPartTotalSets(
                grpId, makeYyyyMmW(yyyyMmW)
        );

        return makeTargetPartTotalSetsDTO(entities);
    }

    /**
     * 10. 그룹 N월간 운동부위별 총 세트수 조회
     *  - IN = [ 그룹ID, 이번월(YYYY/MM) ]
     *  - OUT = [ 부위:총세트수 의 DTO 리스트]
     * */
    @Override
    public TargetPartTotalSetsDTO getMonthlyGrpTargetPartTotalSets(Long grpId, String yyyyMm) {

        List<Object[]> entities = statRepository.findMonthGrpTargetPartTotalSets(
                grpId, makeYyyyMm(yyyyMm)
        );

        return makeTargetPartTotalSetsDTO(entities);
    }

    /**
     * 11. 그룹내 멤버별 N주간 운동부위별 총 세트수 조회
     *  - IN = [ 그룹ID, 이번주(YYYY/MM/W) ]
     *  - OUT = [ 회원:[운동부위:총세트수] 의 DTO 리스트]
     * */
    @Override
    public GrpMbrTargetPartTotalSetsDTO getWeeklyGrpMbrTargetPartTotalSets(Long grpId, String yyyyMmW) {

        // (0) 현재 Week 정보 생성
        YyyyMmW curYyyyMmW = makeYyyyMmW(yyyyMmW);

        // (1) 그룹에 가입한 모든 회원ID 조회
        List<Object[]> members = memberGrpRepository.findMemberGrpsByGrpId(grpId);

        // (2) 모든 회원에 대한 [운동종목:세트수] 정보 처리
        GrpMbrTargetPartTotalSetsDTO grpMbrTargetPartTotalSetsDTO =
                GrpMbrTargetPartTotalSetsDTO.builder().build();

        for (Object[] member : members) {

            // (2-1) 각 회원에 대한 정보 생성
            Long mbrId = (Long) member[1];
            String mbrNm = (String) member[2];
            String profImgPath = (String) member[3];

            List<Object[]> methodTotalSets = statRepository.findWeeklyMethodTotalSets(mbrId, curYyyyMmW);

            
            // (2-2) 각 회원에 대한 정보DTO 조립
            MbrTargetPartTotalSetsDTO mbrTargetPartTotalSetsDTO =
                    MbrTargetPartTotalSetsDTO.builder().build();

            mbrTargetPartTotalSetsDTO.setMbrId(mbrId);
            mbrTargetPartTotalSetsDTO.setMbrNm(mbrNm);
            mbrTargetPartTotalSetsDTO.setProfImgPath(profImgPath);
            mbrTargetPartTotalSetsDTO.setTargetPartTotalSetsDTO(
                    makeTargetPartTotalSetsDTO(methodTotalSets)
            );

            // (2-3) 최종 DTO에 set
            grpMbrTargetPartTotalSetsDTO.getMbrTargetPartTotalSetsDTOList().add(
                    mbrTargetPartTotalSetsDTO
            );
        }

        return grpMbrTargetPartTotalSetsDTO;

    }

    /**
     * 12. 그룹내 멤버별 N월간 운동부위별 총 세트수 조회
     *  - IN = [ 그룹ID, 이번월(YYYY/MM) ]
     *  - OUT = [ 회원:[운동부위:총세트수] 의 DTO 리스트]
     * */
    @Override
    public GrpMbrTargetPartTotalSetsDTO getMonthlyGrpMbrTargetPartTotalSets(Long grpId, String yyyyMm) {
        
        // (0) 현재 Month 정보 생성
        YyyyMm curYyyyMm = makeYyyyMm(yyyyMm);

        // (1) 그룹에 가입한 모든 회원ID 조회
        List<Object[]> members = memberGrpRepository.findMemberGrpsByGrpId(grpId);

        // (2) 모든 회원에 대한 [운동종목:세트수] 정보 처리
        GrpMbrTargetPartTotalSetsDTO grpMbrTargetPartTotalSetsDTO =
                GrpMbrTargetPartTotalSetsDTO.builder().build();

        for (Object[] member : members) {

            // (2-1) 각 회원에 대한 정보 생성
            Long mbrId = (Long) member[1];
            String mbrNm = (String) member[2];
            String profImgPath = (String) member[3];

            List<Object[]> methodTotalSets = statRepository.findMonthlyMethodTotalSets(mbrId, curYyyyMm);


            // (2-2) 각 회원에 대한 정보DTO 조립
            MbrTargetPartTotalSetsDTO mbrTargetPartTotalSetsDTO =
                    MbrTargetPartTotalSetsDTO.builder().build();

            mbrTargetPartTotalSetsDTO.setMbrId(mbrId);
            mbrTargetPartTotalSetsDTO.setMbrNm(mbrNm);
            mbrTargetPartTotalSetsDTO.setProfImgPath(profImgPath);
            mbrTargetPartTotalSetsDTO.setTargetPartTotalSetsDTO(
                    makeTargetPartTotalSetsDTO(methodTotalSets)
            );

            // (2-3) 최종 DTO에 set
            grpMbrTargetPartTotalSetsDTO.getMbrTargetPartTotalSetsDTOList().add(
                    mbrTargetPartTotalSetsDTO
            );
        }

        return grpMbrTargetPartTotalSetsDTO;
    }

    /**
     * 13. 주간 문자발송처리현황조회
     *  - IN = [ YYYY/MM/W ]
     *  - OUT = [ 성공건수/실패건수 DTO ]
     * */
    @Override
    public SmsSendSuccessFailCntDTO getWeeklySmsSendSuccessFailCnt(String yyyyMmW) {
        Optional<WeeklyWkoutStatSchedule> curSchedule = weeklyWkoutStatScheduleRepository.findById(makeYyyyMmW(yyyyMmW));

        // (0) 아직 문자발송 스케줄이 완료되지 않았다면, 0/0 건을 리턴한다.
        if (curSchedule.isEmpty() ||
                curSchedule.get().getStatCplnYn().equals("N")) {
            return SmsSendSuccessFailCntDTO.builder().build();
        }

        // (1) 성공 건수를 조회한다.
        Long successCnt = weeklyWkoutStatRsltRepository.findWeeklyTotalCntBySmsSendRsltClsfCd(
                makeYyyyMmW(yyyyMmW),
                "01"
        );

        // (2) 실패 건수를 조회한다.
        Long failCnt = weeklyWkoutStatRsltRepository.findWeeklyTotalCntBySmsSendRsltClsfCd(
                makeYyyyMmW(yyyyMmW),
                "02"
        );

        return SmsSendSuccessFailCntDTO.builder()
                .successCnt(successCnt)
                .failCnt(failCnt)
                .build();

    }

    /**
     * 13. 주간 문자발송처리현황조회
     *  - IN = [ YYYY/MM/W ]
     *  - OUT = [ 성공건수/실패건수 DTO ]
     * */
    @Override
    public SmsSendSuccessFailCntDTO getMonthlySmsSendSuccessFailCnt(String yyyyMm) {
        Optional<MonthlyWkoutStatSchedule> curSchedule = monthlyWkoutStatScheduleRepository.findById(makeYyyyMm(yyyyMm));

        // (0) 아직 문자발송 스케줄이 완료되지 않았다면, 0/0 건을 리턴한다.
        if (curSchedule.isEmpty() ||
                curSchedule.get().getStatCplnYn().equals("N")) {
            return SmsSendSuccessFailCntDTO.builder().build();
        }

        // (1) 성공 건수를 조회한다.
        Long successCnt = monthlyWkoutStatRsltRepository.findMonthlyTotalCntBySmsSendRsltClsfCd(
                makeYyyyMm(yyyyMm),
                "01"
        );

        // (2) 실패 건수를 조회한다.
        Long failCnt = monthlyWkoutStatRsltRepository.findMonthlyTotalCntBySmsSendRsltClsfCd(
                makeYyyyMm(yyyyMm),
                "02"
        );

        return SmsSendSuccessFailCntDTO.builder()
                .successCnt(successCnt)
                .failCnt(failCnt)
                .build();

    }

}
