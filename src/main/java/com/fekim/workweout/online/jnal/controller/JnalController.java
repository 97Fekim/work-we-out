package com.fekim.workweout.online.jnal.controller;

import com.fekim.workweout.online.member.repository.entity.Member;
import com.fekim.workweout.online.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 운동일지 서비스 MVC 컨트롤러
 * */
@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/jnal")
public class JnalController {

    private final MemberService memberService;

    @GetMapping("/my-calendar")
    public void showCalendar() {

    }

    @GetMapping("/read-one-day")
    public void readOneDayJnals(@RequestParam("yyyyMmDd") String yyyyMmDd,
                                Model model) {
        model.addAttribute("curYyyyMmDd", yyyyMmDd);
    }

    @GetMapping("/register")
    public void registerJnal() {

    }

    @GetMapping("/modify")
    public String modifyJnal(HttpSession session,
                             HttpServletRequest request,
                             @RequestParam("jnalId") Long jnalId,
                             Model model) {
        /* 본인이 속하지 않은 운동일지에 접근시, 이전 페이지로 보낸다. */
        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");
        if (!memberService.isJnalOfMember(mbrId, jnalId)) {
            return "redirect:" + request.getHeader("Referer");
        }

        model.addAttribute("jnalId", jnalId);

        return "jnal/modify";
    }

}
