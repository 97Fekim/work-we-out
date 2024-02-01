package com.fekim.workweout.online.group.controller;

import com.fekim.workweout.online.group.service.GrpService;
import com.fekim.workweout.online.group.service.dto.GrpDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 그룹 서비스 MVC 컨트롤러
 * */
@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/grp")
public class GrpController {

    private final GrpService grpService;

    @GetMapping("/grp-info")
    public void grpInfo(@RequestParam("grpId") Long grpId,
                             Model model) {

        GrpDTO grpDTO = grpService.getGrpInfo(grpId);

        model.addAttribute("grpDTO", grpDTO);
    }

    @GetMapping("/grp-calendar")
    public void showGrpCalendar(@RequestParam("grpId") Long grpId,
                        Model model) {

        model.addAttribute("grpId", grpId);
    }

}
