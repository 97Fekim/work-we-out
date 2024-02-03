package com.fekim.workweout.online.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 인가되지 않은 사용자의 접근을 처리한다.
 *  - EX) USER_ROLE 사용자의  ADMIN_ROLE 페이지 접근
 * */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("[DEBUG]===================CustomAccessDeniedHandler Start==============");

        response.setContentType("text/plain;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().flush();
        response.getWriter().close();
    }
}