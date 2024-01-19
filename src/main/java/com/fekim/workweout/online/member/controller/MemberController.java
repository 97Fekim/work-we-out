package com.fekim.workweout.online.member.controller;

import com.fekim.workweout.online.member.repository.entity.Member;
import com.fekim.workweout.online.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/login")
    public ResponseEntity<String> login(HttpSession session, String email, String password) throws AuthenticationException {

        try {
            memberService.login(session, email, password);
        } catch (AuthenticationException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok("LOGIN OK");

    }

    @GetMapping("/member/connect")
    public ResponseEntity<String> connect(HttpSession session) {
        String result;

        Object email = session.getAttribute("LOGIN_USER");
        if (email == null) {
            result = "로그인 되지 않음";
        } else {
            result = (String) email;
        }

        return ResponseEntity.ok(result);
    }

}
