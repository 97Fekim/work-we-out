package com.fekim.workweout.online.stat.controller;

import com.fekim.workweout.online.date.repository.DateRepository;
import com.fekim.workweout.online.stat.service.StatService;
import com.fekim.workweout.online.stat.service.dto.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/stat/")
@RequiredArgsConstructor
public class StatApiController {

    private final StatService statService;
    private final DateRepository dateRepository;

    /**
     * 01. [개인-주간] 개인 운동 부위별 총 세트 수 조회
     * - IN = YYYY/MM/W
     * - OUT = 운동 부위별 총 세트 수  통계DTO
     * */
    @GetMapping("/my-weekly-total-sets")
    ResponseEntity<TargetPartTotalSetsDTO> getMyWeeklyTotalSets(HttpSession session,
                                                                @RequestParam("yyyyMmW") String yyyyMmW) {

        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");

        TargetPartTotalSetsDTO targetPartTotalSets = statService.getWeeklyTargetPartTotalSets(mbrId, yyyyMmW);

        return new ResponseEntity<>(targetPartTotalSets, HttpStatus.OK);
    }

    /**
     * 02. [개인-주간] 개인 운동 종목별 중량 증감
     * - IN = YYYY/MM/W
     * - OUT = 운동 종목별 중량 증감  통계DTO
     * */
    @GetMapping("/my-weekly-method-wei-incs")
    ResponseEntity<MethodWeiIncsDTO> getMyWeeklyMethodWeiIncs(HttpSession session,
                                                                @RequestParam("yyyyMmW") String yyyyMmW) {

        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");

        List<Object[]> bfYyyyMmWObj = dateRepository.findBeforeCuofYyyyMmW(statService.makeYyyyMmW(yyyyMmW), 1L);
        String bfYyyyMmW = String.valueOf(bfYyyyMmWObj.get(0)[0]) + String.valueOf(bfYyyyMmWObj.get(0)[1]) + String.valueOf(bfYyyyMmWObj.get(0)[2]);

        System.out.println("[DEBUG]===========================================");
        System.out.println("[DEBUG]bfYyyyMmW = " + bfYyyyMmW);
        System.out.println("[DEBUG]curYyyyMmW = " + yyyyMmW);
        System.out.println("[DEBUG]===========================================");

        MethodWeiIncsDTO methodWeiIncs = statService.getWeeklyMethodWeiIncs(mbrId,
                bfYyyyMmW,
                yyyyMmW
        );

        return new ResponseEntity<>(methodWeiIncs, HttpStatus.OK);
    }

    /**
     * 03. [개인-주간] 운동 종목별 중량 상승 추이
     * - IN = YYYY/MM/W
     * - OUT = 운동 종목별 중량 상승 추이  통계DTO
     * */
    @GetMapping("/my-weekly-method-week-max-weis")
    ResponseEntity<MethodWeekMaxWeisDTO> getMyWeeklyMethodWeekMaxWeis(HttpSession session,
                                                                      @RequestParam("yyyyMmW") String yyyyMmW) {

        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");

        MethodWeekMaxWeisDTO methodWeekMaxWeis = statService.getMethodWeekMaxWeis(mbrId, yyyyMmW, 4);

        return new ResponseEntity<>(methodWeekMaxWeis, HttpStatus.OK);
    }

    /**
     * 04. [개인-월간] 개인 운동 부위별 총 세트 수 조회
     * - IN = YYYY/MM
     * - OUT = 운동 부위별 총 세트 수  통계DTO
     * */
    @GetMapping("/my-monthly-total-sets")
    ResponseEntity<TargetPartTotalSetsDTO> getMyMonthlyTotalSets(HttpSession session,
                                                                @RequestParam("yyyyMm") String yyyyMm) {

        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");

        TargetPartTotalSetsDTO targetPartTotalSets = statService.getMonthlyTargetPartTotalSets(mbrId, yyyyMm);

        return new ResponseEntity<>(targetPartTotalSets, HttpStatus.OK);
    }

    /**
     * 05. [개인-월간] 개인 운동 종목별 중량 증감
     * - IN = YYYY/MM
     * - OUT = 운동 종목별 중량 증감  통계DTO
     * */
    @GetMapping("/my-monthly-method-wei-incs")
    ResponseEntity<MethodWeiIncsDTO> getMyMonthlyMethodWeiIncs(HttpSession session,
                                                              @RequestParam("yyyyMm") String yyyyMm) {

        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");

        List<Object[]> bfYyyyMmObj = dateRepository.findBeforeCuofYyyyMm(statService.makeYyyyMm(yyyyMm), 1L);
        String bfYyyyMm = String.valueOf(bfYyyyMmObj.get(0)[0]) + String.valueOf(bfYyyyMmObj.get(0)[1]);

        System.out.println("[DEBUG]===========================================");
        System.out.println("[DEBUG]bfYyyyMmW = " + bfYyyyMm);
        System.out.println("[DEBUG]curYyyyMmW = " + yyyyMm);
        System.out.println("[DEBUG]===========================================");

        MethodWeiIncsDTO methodWeiIncs = statService.getMonthlyMethodWeiIncs(mbrId,
                bfYyyyMm,
                yyyyMm
        );

        return new ResponseEntity<>(methodWeiIncs, HttpStatus.OK);
    }

    /**
     * 06. [개인-월간] 운동 종목별 중량 상승 추이
     * - IN = YYYY/MM
     * - OUT = 운동 종목별 중량 상승 추이  통계DTO
     * */
    @GetMapping("/my-monthly-method-week-max-weis")
    ResponseEntity<MethodMonthMaxWeisDTO> getMyMonthlyMethodWeekMaxWeis(HttpSession session,
                                                                      @RequestParam("yyyyMm") String yyyyMm) {

        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");

        MethodMonthMaxWeisDTO methodMonthMaxWeis = statService.getMethodMonthMaxWeis(mbrId, yyyyMm, 4);

        return new ResponseEntity<>(methodMonthMaxWeis, HttpStatus.OK);
    }

    /**
     * 07. [그룹-주간] 멤버별 평균 운동일수 조회
     *  - IN = YYYY/MM/W , 그룹ID
     *  - 멤버별 평균 운동일수 DTO
     * */
    @GetMapping("/grp-weekly-avg-days")
    ResponseEntity<MbrWkoutDaysCntsDTO> getGrpWeeklyAvgDays(@RequestParam("yyyyMmW") String yyyyMmW,
                                @RequestParam("grpId") Long grpId) {
        MbrWkoutDaysCntsDTO avgDays = statService.getWeeklyGrpWkoutDaysCnt(grpId, yyyyMmW);

        return new ResponseEntity(avgDays, HttpStatus.OK);
    }

    /**
     * 08. [그룹-주간] 그룹의 운동분포 조회
     *  - IN = YYYY/MM/W , 그룹ID
     *  - 그룹의 운동분포 DTO
     * */
    @GetMapping("/grp-weekly-dstb-part")
    ResponseEntity<TargetPartTotalSetsDTO> getGrpWeeklyDstbPart(@RequestParam("yyyyMmW") String yyyyMmW,
                                                            @RequestParam("grpId") Long grpId) {
        TargetPartTotalSetsDTO dstbPart = statService.getWeeklyGrpTargetPartTotalSets(grpId, yyyyMmW);

        return new ResponseEntity(dstbPart, HttpStatus.OK);
    }

    /**
     * 09. [그룹-주간] 그룹의 멤버별 운동분포 조회
     *  - IN = YYYY/MM/W , 그룹ID
     *  - 그룹의 운동분포 DTO
     * */
    @GetMapping("/grp-weekly-dstb-part-by-mbr")
    ResponseEntity<GrpMbrTargetPartTotalSetsDTO> getGrpWeeklyDstbPartByMbr(@RequestParam("yyyyMmW") String yyyyMmW,
                                                                     @RequestParam("grpId") Long grpId) {
        GrpMbrTargetPartTotalSetsDTO dstbPartByMbr = statService.getWeeklyGrpMbrTargetPartTotalSets(grpId, yyyyMmW);

        return new ResponseEntity(dstbPartByMbr, HttpStatus.OK);
    }

    /**
     * 10. [그룹-월간] 멤버별 평균 운동일수 조회
     *  - IN = YYYY/MM , 그룹ID
     *  - 멤버별 평균 운동일수 DTO
     * */
    @GetMapping("/grp-monthly-avg-days")
    ResponseEntity<MbrWkoutDaysCntsDTO> getGrpMonthlyAvgDays(@RequestParam("yyyyMm") String yyyyMm,
                                                   @RequestParam("grpId") Long grpId) {
        MbrWkoutDaysCntsDTO avgDays = statService.getMonthlyGrpWkoutDaysCnt(grpId, yyyyMm);

        return new ResponseEntity(avgDays, HttpStatus.OK);
    }

    /**
     * 11. [그룹-월간] 그룹의 운동분포 조회
     *  - IN = YYYY/MM , 그룹ID
     *  - 그룹의 운동분포 DTO
     * */
    @GetMapping("/grp-monthly-dstb-part")
    ResponseEntity<TargetPartTotalSetsDTO> getGrpMonthlyDstbPart(@RequestParam("yyyyMm") String yyyyMm,
                                                                 @RequestParam("grpId") Long grpId) {
        TargetPartTotalSetsDTO dstbPart = statService.getMonthlyGrpTargetPartTotalSets(grpId, yyyyMm);

        return new ResponseEntity(dstbPart, HttpStatus.OK);
    }

    /**
     * 12. [그룹-월간] 그룹의 멤버별 운동분포 조회
     *  - IN = YYYY/MM , 그룹ID
     *  - 그룹의 운동분포 DTO
     * */
    @GetMapping("/grp-monthly-dstb-part-by-mbr")
    ResponseEntity<GrpMbrTargetPartTotalSetsDTO> getGrpMonthlyDstbPartByMbr(@RequestParam("yyyyMm") String yyyyMm,
                                                                           @RequestParam("grpId") Long grpId) {
        GrpMbrTargetPartTotalSetsDTO dstbPartByMbr = statService.getMonthlyGrpMbrTargetPartTotalSets(grpId, yyyyMm);

        return new ResponseEntity(dstbPartByMbr, HttpStatus.OK);
    }

    /**
     * 13. [관리-주간] 개인 통계 문자발송 처리건수 조회
     *  - IN = YYYY/MM/W
     *  - 성공건수 및 실패건수 DTO
     * */
    @GetMapping("/manage/weekly-stat-sms-cnt")
    ResponseEntity<SmsSendSuccessFailCntDTO> getWeeklyStatSmsCnt(@RequestParam("yyyyMmW") String yyyyMmW) {
        SmsSendSuccessFailCntDTO successFailCnt = statService.getWeeklySmsSendSuccessFailCnt(yyyyMmW);

        return new ResponseEntity(successFailCnt, HttpStatus.OK);
    }

    /**
     * 14. [관리-월간] 개인 통계 문자발송 처리건수 조회
     *  - IN = YYYY/MM
     *  - 성공건수 및 실패건수 DTO
     * */
    @GetMapping("/manage/monthly-stat-sms-cnt")
    ResponseEntity<SmsSendSuccessFailCntDTO> getMonthlyStatSmsCnt(@RequestParam("yyyyMm") String yyyyMm) {
        SmsSendSuccessFailCntDTO successFailCnt = statService.getMonthlySmsSendSuccessFailCnt(yyyyMm);

        return new ResponseEntity(successFailCnt, HttpStatus.OK);
    }
    

}
