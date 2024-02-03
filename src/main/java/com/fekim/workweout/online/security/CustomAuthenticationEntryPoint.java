package com.fekim.workweout.online.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 인증되지 않은 사용자의 접근을 처리한다.
 *  - EX) 로그인 하지 않은 유저의  USER_ROLE 권한 페이지 접근
 * */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        System.out.println("[DEBUG]===================CustomAuthenticationEntryPoint Start==============");

        // 인증 실패시 로그인화면으로 리다이렉트한다.
        response.sendRedirect("/member/sign-in");
    }
}