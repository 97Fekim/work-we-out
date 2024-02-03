package com.fekim.workweout.online.group.controller;

import com.fekim.workweout.online.group.service.GrpService;
import com.fekim.workweout.online.group.service.dto.GrpDTO;
import com.fekim.workweout.online.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

/**
 * 그룹 서비스 MVC 컨트롤러
 * */
@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/grp")
public class GrpController {

    private final GrpService grpService;
    private final MemberService memberService;

    @GetMapping("/grp-info")
    public String grpInfo(HttpSession session,
                        HttpServletRequest request,
                        @RequestParam("grpId") Long grpId,
                        Model model) {

        /* 본인이 속하지 않은 그룹의 자원에 접근시, 이전 페이지로 되돌려 보낸다. */
        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");
        if (!memberService.isGrpOfMember(mbrId, grpId)) {
            return "redirect:" + request.getHeader("Referer");
        }

        GrpDTO grpDTO = grpService.getGrpInfo(grpId);

        model.addAttribute("grpDTO", grpDTO);

        return "/grp/grp-info";
    }

    @GetMapping("/grp-calendar")
    public String showGrpCalendar(HttpSession session,
                                  HttpServletRequest request,
                                  @RequestParam("grpId") Long grpId,
                                  Model model) {

        /* 본인이 속하지 않은 그룹의 자원에 접근시, 이전 페이지로 되돌려 보낸다. */
        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");
        if (!memberService.isGrpOfMember(mbrId, grpId)) {
            return "redirect:" + request.getHeader("Referer");
        }

        model.addAttribute("grpId", grpId);

        return "/grp/grp-calendar";
    }

}
