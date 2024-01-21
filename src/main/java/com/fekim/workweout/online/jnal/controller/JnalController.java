package com.fekim.workweout.online.jnal.controller;

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

    @GetMapping("/calendar")
    public void showCalendar(HttpSession session, @RequestParam Long mbrId) {
        System.out.println("========mbrId = " + mbrId);
        System.out.println("========session Info = " + session.getId());
    }


}
