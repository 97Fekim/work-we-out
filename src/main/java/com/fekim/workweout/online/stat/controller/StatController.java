package com.fekim.workweout.online.stat.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 개인 통계 서비스 MVC 컨트롤러
 * */
@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/stat")
public class StatController {

    @GetMapping("/my-stat")
    public void myStat() {

    }

    @GetMapping("/grp-stat")
    public void grpStat(@RequestParam("grpId") Long grpId,
                        Model model) {
        model.addAttribute("grpId", grpId);
    }

    @GetMapping("/manage/stat-sms-mng")
    public void statSmsMng() {

    }

}
