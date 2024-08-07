package com.fekim.workweout.online.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final CustomUserAuthenticationFilter userAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) ->
                        csrf.disable()
                )
                .formLogin((formLogin) ->
                        formLogin.disable()
                )
                .logout((logout) ->
                        logout.disable()
                )
                .headers((headersConfig) ->
                        headersConfig.disable()
                )
                .httpBasic((httpBasic) ->
                        httpBasic.disable()
                )
                .rememberMe((rememberMe) ->
                        rememberMe.disable()
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/h2-console", "/h2-console/**").permitAll()
                                .requestMatchers("member/sign-up", "member/sign-in").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/stat/manage/", "/stat/manage/**").hasRole("ADMIN")
                                .requestMatchers("/**").hasAnyRole("USER")
                )
                .cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource)
                )
                .sessionManagement((sessionManagementConfig) ->
                        sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .exceptionHandling((exceptionHandlingConfig) ->
                        exceptionHandlingConfig
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler)
                )
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }

    /* 정적 자원에 대한 접근권한 해제 */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(
                        PathRequest
                        .toStaticResources()
                        .atCommonLocations()
                );
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}