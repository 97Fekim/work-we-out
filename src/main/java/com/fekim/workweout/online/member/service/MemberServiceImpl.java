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

        /* In DTO에 값이 존재하면 변경. */
        if (memberDTO.getEmail() != null && memberDTO.getEmail().isEmpty()) {
            originEntity.setEmail(memberDTO.getEmail());
        }
        if (memberDTO.getMbrNm()!= null && !memberDTO.getMbrNm().isEmpty()) {
            originEntity.setMbrNm(memberDTO.getMbrNm());
        }
        if (memberDTO.getPhone() != null && !memberDTO.getPhone().isEmpty()) {
            originEntity.setPhone(memberDTO.getPhone());
        }
        if (memberDTO.getStatSmsSendYn() != null && !memberDTO.getStatSmsSendYn().isEmpty()) {
            originEntity.setStatSmsSendYn(memberDTO.getStatSmsSendYn());
        }
    }

    /**
     * 03. 회원가입
     *  - IN = 회원DTO
     *  - OUT = 가입된 회원DTO
     * */
    @Override
    public MemberDTO registerMember(MemberRegisterDTO memberRegisterDTO) throws SecurityException {

        /* 예외처리 */
        // ▼ 결과코드 명세 ▼
        //  - 01 : 이메일 미입력
        //  - 02 : 비밀번호 미입력
        //  - 03 : 기존재하는 이메일
        // 이미 존재하는 이메일인 경우
        if (memberRegisterDTO.getEmail() == null || memberRegisterDTO.getEmail().isEmpty()) {
            throw new SecurityException("01");
        }
        if (memberRegisterDTO.getPassword() == null || memberRegisterDTO.getPassword().isEmpty()) {
            throw new SecurityException("02");
        }
        if (memberRepository.findByEmail(memberRegisterDTO.getEmail()).isPresent()) {
            throw new SecurityException("03");
        }

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
    public void login(HttpSession session, String email, String password) throws SecurityException {

        Optional<Member> member = memberRepository.findByEmail(email);

        /* (1) 이메일 미존재 */
        if (member.isEmpty()) {
            throw new SecurityException("01");
        }

        /* (2) 비밀번호 불일치 */
        if (!member.get().isPasswordMatch(passwordEncoder, password)) {
            throw new SecurityException("02");
        }

        session.setAttribute("LOGIN_MEMBER", member.get().getMbrId());

    }

    /**
     * 05. 로그아웃
     *  - IN : []
     *  - OUT : []
     * */
    public void logout(HttpSession session) {
        session.removeAttribute("LOGIN_USER");
    }

    /**
     * 06. 회원-운동일지 일치여부 판정
     *  - IN : 회원ID , 운동일지ID
     *  - OUT :
     *    회원-운동일지 일치 : true
     *    회원-운동일지 불일치 : false
     * */
    public boolean isJnalOfMember(Long mbrId, Long jnalId) {
        return memberRepository.findJnalByMbrIdAndJnalId(mbrId, jnalId).isPresent();
    }

    /**
     * 07. 회원-그룹 일치여부 판정
     *  - IN : 회원ID , 그룹ID
     *  - OUT :
     *    회원-그룹 일치 : true
     *    회원-그룹 불일치 : false
     * */
    public boolean isGrpOfMember(Long mbrId, Long grpId) {
        return memberRepository.findMemberGrpByMbrIdAndGrpId(mbrId, grpId).isPresent();
    }

}
