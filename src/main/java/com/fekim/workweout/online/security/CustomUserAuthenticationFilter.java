package com.fekim.workweout.online.security;


import com.fekim.workweout.online.member.repository.MemberRepository;
import com.fekim.workweout.online.member.repository.entity.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomUserAuthenticationFilter extends OncePerRequestFilter {

    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("[DEBUG]===================CustomUserAuthenticationFilter Start==============");

        Member member = (Member) request.getSession().getAttribute("LOGIN_MEMBER");

        if (member != null) {
            SecurityContextHolder.getContext().setAuthentication(member.makeAuthentication());
        }

        filterChain.doFilter(request, response);

    }
}