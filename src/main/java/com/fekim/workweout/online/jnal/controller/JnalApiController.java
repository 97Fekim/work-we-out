package com.fekim.workweout.online.jnal.controller;

import com.fekim.workweout.online.jnal.service.WkoutJnalService;
import com.fekim.workweout.online.jnal.service.dto.OneDayJnalsDTO;
import com.fekim.workweout.online.jnal.service.dto.OneMonthCalendarDTO;
import com.fekim.workweout.online.member.repository.entity.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/jnal/")
@RequiredArgsConstructor
public class JnalApiController {

    private final WkoutJnalService jnalService;

    @GetMapping("/calendar")
    ResponseEntity<OneMonthCalendarDTO> getOneMonthCalendar(HttpSession session, @RequestParam("yyyyMm") String yyyyMm) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        OneMonthCalendarDTO oneMonthCalendarDTO = jnalService.getOneMonthCalendar(mbrId, yyyyMm);

        return new ResponseEntity<>(oneMonthCalendarDTO, HttpStatus.OK);
    }

    @GetMapping("/one-day-jnals")
    ResponseEntity<OneDayJnalsDTO> getOneDayJnals(HttpSession session, @RequestParam("yyyyMmDd") String yyyyMmDd) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        OneDayJnalsDTO oneDayJnalsDTO = jnalService.getOneDayJnals(mbrId, yyyyMmDd, "01");

        return new ResponseEntity<>(oneDayJnalsDTO, HttpStatus.OK);
    }


}
