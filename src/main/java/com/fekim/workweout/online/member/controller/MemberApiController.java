package com.fekim.workweout.online.member.controller;

import com.fekim.workweout.online.jnal.service.dto.WkoutJnalDTO;
import com.fekim.workweout.online.member.repository.entity.Member;
import com.fekim.workweout.online.member.service.MemberService;
import com.fekim.workweout.online.member.service.dto.MemberDTO;
import com.fekim.workweout.online.member.service.dto.MemberRegisterDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 01. 로그인
     *  - IN = 이메일, 비밀번호
     *  - OUT = 로그인 결과 코드 (01:이메일미존재 / 02:비밀번호불일치 / 03:성공)
     * */
    @PostMapping("/sign-in")
    public ResponseEntity<String> login(HttpSession session,
                                        @RequestParam("email") String email,
                                        @RequestParam("password") String password) {

        try {
            memberService.login(session, email, password);
        } catch (SecurityException e) {
            if (e.getMessage().equals("01")) {
                // 이메일 미존재
                return new ResponseEntity("01", HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (e.getMessage().equals("02")) {
                // 비밀번호 불일치
                return new ResponseEntity("02", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        // 로그인 성공
        return new ResponseEntity<>("00", HttpStatus.OK);

    }

    /**
     * 02. 회원가입
     *  - IN = 회원DTO
     *  - OUT = 회원가입 결과코드 (00:정상 / 01:이메일미입력 / 02:비밀번호미입력 / 03:기존재하는이메일)
     * */
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody MemberRegisterDTO memberRegisterDTO) {

        try {
            memberService.registerMember(memberRegisterDTO);
        } catch (SecurityException e) {
            switch (e.getMessage()) {
                case "01" : {
                    return new ResponseEntity<>("01", HttpStatus.INTERNAL_SERVER_ERROR);
                } case "02" : {
                    return new ResponseEntity<>("02", HttpStatus.INTERNAL_SERVER_ERROR);
                } case "03" : {
                    return new ResponseEntity<>("03", HttpStatus.INTERNAL_SERVER_ERROR);
                } default : {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }

        return new ResponseEntity<>("00", HttpStatus.OK);
    }

    /**
     * 03. 회원정보 수정
     *  - IN = 회원정보DTO
     *  - OUT = 수정된 회원ID
     * */
    @PostMapping("/modify")
    ResponseEntity<Long> modifyInfo(HttpSession session,
                                      @RequestBody MemberDTO memberDTO) {
        Long mbrId = (Long) session.getAttribute("LOGIN_MEMBER");

        memberDTO.setMbrId(mbrId);

        memberService.modifyInfo(memberDTO);

        return new ResponseEntity<>(mbrId, HttpStatus.OK);
    }

}
