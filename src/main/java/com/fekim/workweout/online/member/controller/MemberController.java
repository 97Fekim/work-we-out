package com.fekim.workweout.online.member.controller;

import com.fekim.workweout.online.member.repository.entity.Member;
import com.fekim.workweout.online.member.service.MemberService;
import com.fekim.workweout.online.member.service.dto.MemberDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 회원 서비스 MVC 컨트롤러
 * */
@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    @Value("${albDnsName}")
    private String albDnsName;

    @Value("${batchDnsName}")
    private String batchDnsName;

    private final MemberService memberService;

    /* 회원가입 페이지으로 이동 */
    @GetMapping("/sign-up")
    public void singUp() {

    }

    /* 로그인 페이지으로 이동 */
    @GetMapping("/sign-in")
    public void singIn(Model model) {
        model.addAttribute("albDnsName", albDnsName);
        model.addAttribute("batchDnsName", batchDnsName);
    }

    /* 내 정보 페이지으로 이동 */
    @GetMapping("/my-info")
    public void myInfo(HttpSession session, Model model) {
        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");

        MemberDTO memberDTO = memberService.getNormalMember(mbrId);

        model.addAttribute("memberDTO", memberDTO);
    }

    /* 내 정보 편집 페이지으로 이동 */
    @GetMapping("/modify")
    public void modifyInfo(HttpSession session, Model model) {
        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");

        MemberDTO memberDTO = memberService.getNormalMember(mbrId);

        model.addAttribute("memberDTO", memberDTO);
    }

}
