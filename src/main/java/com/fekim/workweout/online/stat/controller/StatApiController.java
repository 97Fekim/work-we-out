package com.fekim.workweout.online.stat.controller;

import com.fekim.workweout.online.date.repository.DateRepository;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.jnal.service.dto.WkoutMethodListDTO;
import com.fekim.workweout.online.member.repository.entity.Member;
import com.fekim.workweout.online.stat.service.StatService;
import com.fekim.workweout.online.stat.service.dto.MethodWeekMaxWeisDTO;
import com.fekim.workweout.online.stat.service.dto.MethodWeiIncsDTO;
import com.fekim.workweout.online.stat.service.dto.TargetPartTotalSetsDTO;
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

    @GetMapping("/my-weekly-total-sets")
    ResponseEntity<TargetPartTotalSetsDTO> getMyWeeklyTotalSets(HttpSession session,
                                                                @RequestParam("yyyyMmW") String yyyyMmW) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        TargetPartTotalSetsDTO targetPartTotalSets = statService.getWeeklyTargetPartTotalSets(mbrId, yyyyMmW);

        return new ResponseEntity<>(targetPartTotalSets, HttpStatus.OK);
    }

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

    @GetMapping("/my-weekly-method-week-max-weis")
    ResponseEntity<MethodWeekMaxWeisDTO> getMyWeeklyMethodWeekMaxWeis(HttpSession session,
                                                                      @RequestParam("yyyyMmW") String yyyyMmW) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        MethodWeekMaxWeisDTO methodWeekMaxWeis = statService.getMethodWeekMaxWeis(mbrId, yyyyMmW, 4);

        return new ResponseEntity<>(methodWeekMaxWeis, HttpStatus.OK);
    }

}
