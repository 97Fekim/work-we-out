package com.fekim.workweout.online.group.controller;

import com.fekim.workweout.online.group.service.GrpService;
import com.fekim.workweout.online.group.service.dto.GrpDTO;
import com.fekim.workweout.online.group.service.dto.GrpListDTO;
import com.fekim.workweout.online.group.service.dto.OneMonthGrpCalendarDTO;
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
@RequestMapping("/grp/")
@RequiredArgsConstructor
public class GrpApiController {

    private final GrpService grpService;

    /**
     * 00. 그룹 정보 조회
     * - IN = [그룹ID]
     * - OUT = 그룹 DTO
     * */
    @GetMapping("/one-grp")
    ResponseEntity<GrpDTO> getGrp(@RequestParam("grpId") Long grpId) {
        GrpDTO grpDTO = grpService.getGrpInfo(grpId);
        return new ResponseEntity<>(grpDTO, HttpStatus.OK);
    }
    
    /**
     * 01. 내 모든 그룹 조회
     * - IN = []
     * - OUT = 모든 그룹 리스트 DTO
     * */
    @GetMapping("/my-all")
    ResponseEntity<GrpListDTO> getMyGrps(HttpSession session) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        GrpListDTO grpListDTO = grpService.getGrpListByMbrId(mbrId);

        return new ResponseEntity<>(grpListDTO, HttpStatus.OK);
    }

    /**
     * 02. 신규그룹 생성
     * - IN = 그룹이름
     * - OUT = 신규그룹ID
     * */
    @PostMapping("/register")
    ResponseEntity<Long> registerGrp(HttpSession session,
                              @RequestParam("grpNm") String grpNm) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        GrpDTO grpDTO = grpService.createGrp(grpNm, mbrId);

        return new ResponseEntity<>(grpDTO.getGrpId(), HttpStatus.OK);
    }

    /**
     * 03. 그룹 캘린더 조회
     * - IN = 기준월 (YYYY/MM), 그룹아이디(grpId)
     * - OUT = 달력에 표시되는 모든 Grid Element
     * */
    @GetMapping("/calendar")
    ResponseEntity<OneMonthGrpCalendarDTO> getOneMonthCalendar(@RequestParam("yyyyMm") String yyyyMm,
                                                            @RequestParam("grpId") Long grpId) {

        OneMonthGrpCalendarDTO oneMonthGrpCalendarDTO = grpService.getOneMonthGrpCalendar(grpId, yyyyMm);

        return new ResponseEntity<>(oneMonthGrpCalendarDTO, HttpStatus.OK);
    }

    /**
     * 04. 그룹 오늘의 운동일지 리스트 조회
     * - IN = YYYY/MM/DD
     * - OUT = 그룹이 하루동안 운동한 모든 운동일지 리스트 DTO
     * */
    @GetMapping("/one-day-jnals")
    ResponseEntity<OneDayJnalsDTO> getOneDayJnals(@RequestParam("yyyyMmDd") String yyyyMmDd,
                                                  @RequestParam("grpId") Long grpId) {

        OneDayJnalsDTO oneDayJnalsDTO = grpService.getOneDayGrpJnals(grpId, yyyyMmDd);

        return new ResponseEntity<>(oneDayJnalsDTO, HttpStatus.OK);
    }


}
