package com.fekim.workweout.online.jnal.controller;

import com.fekim.workweout.online.member.repository.entity.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/jnal")
public class JnalController {

    @GetMapping("/myCalendar")
    public void showCalendar(HttpSession session) {

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");
        Long mbrId = member.getMbrId();

    }


}
