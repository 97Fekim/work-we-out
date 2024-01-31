package com.fekim.workweout.online.stat.controller;

import com.fekim.workweout.online.date.repository.DateRepository;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.jnal.service.dto.WkoutMethodListDTO;
import com.fekim.workweout.online.member.repository.entity.Member;
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
     * 01. [주간] 개인 운동 부위별 총 세트 수 조회
     * - IN = YYYY/MM/W
     * - OUT = 운동 부위별 총 세트 수  통계DTO
     * */
    @GetMapping("/my-weekly-total-sets")
    ResponseEntity<TargetPartTotalSetsDTO> getMyWeeklyTotalSets(HttpSession session,
                                                                @RequestParam("yyyyMmW") String yyyyMmW) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        TargetPartTotalSetsDTO targetPartTotalSets = statService.getWeeklyTargetPartTotalSets(mbrId, yyyyMmW);

        return new ResponseEntity<>(targetPartTotalSets, HttpStatus.OK);
    }

    /**
     * 02. [주간] 개인 운동 종목별 중량 증감
     * - IN = YYYY/MM/W
     * - OUT = 운동 종목별 중량 증감  통계DTO
     * */
    @GetMapping("/my-weekly-method-wei-incs")
    ResponseEntity<MethodWeiIncsDTO> getMyWeeklyMethodWeiIncs(HttpSession session,
                                                                @RequestParam("yyyyMmW") String yyyyMmW) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

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
     * 03. [주간] 운동 종목별 중량 상승 추이
     * - IN = YYYY/MM/W
     * - OUT = 운동 종목별 중량 상승 추이  통계DTO
     * */
    @GetMapping("/my-weekly-method-week-max-weis")
    ResponseEntity<MethodWeekMaxWeisDTO> getMyWeeklyMethodWeekMaxWeis(HttpSession session,
                                                                      @RequestParam("yyyyMmW") String yyyyMmW) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        MethodWeekMaxWeisDTO methodWeekMaxWeis = statService.getMethodWeekMaxWeis(mbrId, yyyyMmW, 4);

        return new ResponseEntity<>(methodWeekMaxWeis, HttpStatus.OK);
    }

    /**
     * 04. [월간] 개인 운동 부위별 총 세트 수 조회
     * - IN = YYYY/MM
     * - OUT = 운동 부위별 총 세트 수  통계DTO
     * */
    @GetMapping("/my-monthly-total-sets")
    ResponseEntity<TargetPartTotalSetsDTO> getMyMonthlyTotalSets(HttpSession session,
                                                                @RequestParam("yyyyMm") String yyyyMm) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        TargetPartTotalSetsDTO targetPartTotalSets = statService.getMonthlyTargetPartTotalSets(mbrId, yyyyMm);

        return new ResponseEntity<>(targetPartTotalSets, HttpStatus.OK);
    }

    /**
     * 05. [월간] 개인 운동 종목별 중량 증감
     * - IN = YYYY/MM
     * - OUT = 운동 종목별 중량 증감  통계DTO
     * */
    @GetMapping("/my-monthly-method-wei-incs")
    ResponseEntity<MethodWeiIncsDTO> getMyMonthlyMethodWeiIncs(HttpSession session,
                                                              @RequestParam("yyyyMm") String yyyyMm) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

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
     * 06. [월간] 운동 종목별 중량 상승 추이
     * - IN = YYYY/MM
     * - OUT = 운동 종목별 중량 상승 추이  통계DTO
     * */
    @GetMapping("/my-monthly-method-week-max-weis")
    ResponseEntity<MethodMonthMaxWeisDTO> getMyMonthlyMethodWeekMaxWeis(HttpSession session,
                                                                      @RequestParam("yyyyMm") String yyyyMm) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        MethodMonthMaxWeisDTO methodMonthMaxWeis = statService.getMethodMonthMaxWeis(mbrId, yyyyMm, 4);

        return new ResponseEntity<>(methodMonthMaxWeis, HttpStatus.OK);
    }
}
