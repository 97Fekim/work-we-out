package com.fekim.workweout.online.member.controller;

import com.fekim.workweout.online.member.repository.entity.Member;
import com.fekim.workweout.online.member.service.MemberService;
import com.fekim.workweout.online.member.service.dto.MemberDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/login")
    public ResponseEntity<String> login(HttpSession session, String email, String password) {

        try {
            memberService.login(session, email, password);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok("LOGIN OK");

    }

}
