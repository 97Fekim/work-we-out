package com.fekim.workweout.online.jnal.controller;

import com.fekim.workweout.online.jnal.service.WkoutJnalService;
import com.fekim.workweout.online.jnal.service.dto.OneDayJnalsDTO;
import com.fekim.workweout.online.jnal.service.dto.OneMonthCalendarDTO;
import com.fekim.workweout.online.jnal.service.dto.WkoutJnalDTO;
import com.fekim.workweout.online.jnal.service.dto.WkoutMethodListDTO;
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

    /**
     * 01. 모든 운동종목 조회
     * - IN = []
     * - OUT = 모든 운동종목 리스트 DTO
     * */
    @GetMapping("/all-method")
    ResponseEntity<WkoutMethodListDTO> getAllMethods() {
        WkoutMethodListDTO wkoutMethodListDTO = jnalService.getAllWkoutMethod();

        return new ResponseEntity<>(wkoutMethodListDTO, HttpStatus.OK);
    }

    /**
     * 02. 개인 캘린더 조회
     * - IN = 기준월 (YYYY/MM)
     * - OUT = 달력에 표시되는 모든 Grid Element
     * */
    @GetMapping("/calendar")
    ResponseEntity<OneMonthCalendarDTO> getOneMonthCalendar(HttpSession session, @RequestParam("yyyyMm") String yyyyMm) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        OneMonthCalendarDTO oneMonthCalendarDTO = jnalService.getOneMonthCalendar(mbrId, yyyyMm);

        return new ResponseEntity<>(oneMonthCalendarDTO, HttpStatus.OK);
    }

    /**
     * 03. 개인 캘린더 조회
     * - IN = YYYY/MM/DD
     * - OUT = 하루동안 존재하는 모든 운동일지 리스트 DTO
     * */
    @GetMapping("/one-day-jnals")
    ResponseEntity<OneDayJnalsDTO> getOneDayJnals(HttpSession session, @RequestParam("yyyyMmDd") String yyyyMmDd) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        OneDayJnalsDTO oneDayJnalsDTO = jnalService.getOneDayJnals(mbrId, yyyyMmDd, "01");

        return new ResponseEntity<>(oneDayJnalsDTO, HttpStatus.OK);
    }

    /**
     * 04. 운동일지 조회
     * - IN = 운동일지ID
     * - OUT = 운동일지DTO
     * */
    @GetMapping("/read-one")
    ResponseEntity<WkoutJnalDTO> readOneJnal(@RequestParam("jnalId") Long jnalId) {
        WkoutJnalDTO jnalDTO = jnalService.getOneJnal(jnalId);

        return new ResponseEntity<>(jnalDTO, HttpStatus.OK);
    }

    /**
     * 05. 운동일지 삭제
     * - IN = 운동일지ID
     * - OUT = []
     * */
    @PostMapping("/remove")
    ResponseEntity<String> removeJnal(@RequestParam("jnalId") Long jnalId) {

        jnalService.removeJnal(jnalId);
        
        return new ResponseEntity<>("Removed Completely", HttpStatus.OK);
    }

    /**
     * 06. 운동일지 수정
     * - IN = 운동일지수정DTO
     * - OUT = 수정된 운동일지의 날짜 YYYY/MM/DD
     * */
    @PostMapping("/modify")
    ResponseEntity<String> modifyJnal(HttpSession session,
                                    @RequestBody WkoutJnalDTO jnalDTO) {
        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        jnalDTO.setMbrId(mbrId);

        Long jnalId = jnalService.modifyJnal(jnalDTO);

        String newYyyyMmDd = jnalDTO.getYyyy() + jnalDTO.getMm() + jnalDTO.getDd();

        return new ResponseEntity<>(newYyyyMmDd, HttpStatus.OK);
    }

    /**
     * 07. 새 운동일지 저장
     * - IN = 운동일지저장DTO
     * - OUT = 저장된 운동일지의 날짜 YYYY/MM/DD
     * */
    @PostMapping("/register")
    ResponseEntity<String> registerJnal(HttpSession session,
                                        @RequestBody WkoutJnalDTO jnalDTO) {
        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        jnalDTO.setMbrId(mbrId);

        Long jnalId = jnalService.createJnal(jnalDTO, mbrId);

        String yyyyMmDd = jnalDTO.getYyyy() + jnalDTO.getMm() + jnalDTO.getDd();

        return new ResponseEntity<>(yyyyMmDd, HttpStatus.OK);
    }

}
