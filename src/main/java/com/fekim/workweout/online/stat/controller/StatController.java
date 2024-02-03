package com.fekim.workweout.online.stat.controller;

import com.fekim.workweout.online.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 통계 서비스 MVC 컨트롤러
 * */
@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/stat")
public class StatController {

    private final MemberService memberService;

    @GetMapping("/my-stat")
    public void myStat() {

    }

    @GetMapping("/grp-stat")
    public String grpStat(HttpSession session,
                        HttpServletRequest request,
                        @RequestParam("grpId") Long grpId,
                        Model model) {

        /* 본인이 속하지 않은 그룹의 자원에 접근시, 이전 페이지로 되돌려 보낸다. */
        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");
        if (!memberService.isGrpOfMember(mbrId, grpId)) {
            return "redirect:" + request.getHeader("Referer");
        }

        model.addAttribute("grpId", grpId);

        return "/stat/grp-stat";
    }

    @GetMapping("/manage/stat-sms-mng")
    public void statSmsMng() {

    }

}
