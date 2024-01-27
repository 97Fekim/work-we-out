package com.fekim.workweout.online.group.controller;

import com.fekim.workweout.online.group.service.GrpService;
import com.fekim.workweout.online.group.service.dto.GrpDTO;
import com.fekim.workweout.online.group.service.dto.GrpListDTO;
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

    @GetMapping("/my-all")
    ResponseEntity<GrpListDTO> getMyGrps(HttpSession session) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        GrpListDTO grpListDTO = grpService.getGrpListByMbrId(mbrId);

        return new ResponseEntity<>(grpListDTO, HttpStatus.OK);
    }

    @PostMapping("/register")
    ResponseEntity<Long> registerGrp(HttpSession session,
                              @RequestParam("grpNm") String grpNm) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

        GrpDTO grpDTO = grpService.createGrp(grpNm, mbrId);

        return new ResponseEntity<>(grpDTO.getGrpId(), HttpStatus.OK);
    }

}
