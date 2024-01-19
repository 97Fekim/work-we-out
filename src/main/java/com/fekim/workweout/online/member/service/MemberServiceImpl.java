package com.fekim.workweout.online.member.service;

import com.fekim.workweout.online.member.repository.MemberRepository;
import com.fekim.workweout.online.member.repository.entity.Member;
import com.fekim.workweout.online.member.service.dto.MemberDTO;
import com.fekim.workweout.online.member.service.dto.MemberRegisterDTO;
import jakarta.servlet.http.HttpSession;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 01. 정상회원 정보 조회
     *  - IN = 회원ID
     *  - OUT = 회원DTO
     * */
    @Override
    public MemberDTO getNormalMember(Long mbrId) {
        Member member = memberRepository.findMemberByIdAndMbrStatClsfCd(mbrId, "01");
        return entityToDTO(member);
    }

    /**
     * 02. 정상회원 정보 수정
     *  - IN = 회원ID
     *  - OUT = []
     * */
    @Override
    @Transactional // [JPA] Dirty-Checking
    public void modifyInfo(MemberDTO memberDTO) {
        Member originEntity =
                memberRepository.findMemberByIdAndMbrStatClsfCd(memberDTO.getMbrId(), "01");

        originEntity.setEmail(memberDTO.getEmail());
        originEntity.setMbrNm(memberDTO.getMbrNm());
        originEntity.setPhone(memberDTO.getPhone());
        originEntity.setStatSmsSendYn(memberDTO.getStatSmsSendYn());
    }

    /**
     * 03. 회원가입
     *  - IN = 회원DTO
     *  - OUT = 가입된 회원DTO
     * */
    @Override
    public MemberDTO registerMember(MemberRegisterDTO memberRegisterDTO) {

        String password = passwordEncoder.encode(memberRegisterDTO.getPassword());
        String mbrNm = memberRegisterDTO.getMbrNm();
        String email = memberRegisterDTO.getEmail();
        String phone = memberRegisterDTO.getPhone();
        String statSmsSendYn = memberRegisterDTO.getStatSmsSendYn();
        String mbrRoleClsfCd = "ROLE_USER";
        String mbrStatClsfCd = "01";

        Member savedMember = memberRepository.save(
                Member
                    .builder()
                    .password(password)
                    .mbrNm(mbrNm)
                    .email(email)
                    .phone(phone)
                    .statSmsSendYn(statSmsSendYn)
                    .mbrRoleClsfCd(mbrRoleClsfCd)
                    .mbrStatClsfCd(mbrStatClsfCd)
                    .build()
        );

        return entityToDTO(savedMember);

    }

    /**
     * 04. 로그인
     *  - IN : [ 이메일, 비밀번호 ]
     *  - OUt : 세션ID
     * */
    @Transactional(readOnly = true) // 조회용 API임을 명시 + flush X
    public void login(HttpSession session, String email, String password)  {

        Optional<Member> member = memberRepository.findByEmail(email);

        /* (1) 이메일 미존재 */
        if (member.isEmpty()) {
            throw new SecurityException();
        }

        /* (2) 비밀번호 불일치 */
        if (!member.get().isPasswordMatch(passwordEncoder, password)) {
            throw new SecurityException();
        }

        session.setAttribute("LOGIN_MEMBER", member.get().getEmail());

    }

    /**
     * 05. 로그아웃
     *  - IN : []
     *  - OUT : []
     * */
    public void logout(HttpSession session) {
        session.removeAttribute("LOGIN_USER");
    }

}
