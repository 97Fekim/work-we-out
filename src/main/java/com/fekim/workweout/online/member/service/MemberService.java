package com.fekim.workweout.online.member.service;

import com.fekim.workweout.online.member.repository.entity.Member;
import com.fekim.workweout.online.member.service.dto.MemberDTO;
import com.fekim.workweout.online.member.service.dto.MemberRegisterDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;

public interface MemberService {

    /**
     * 01. 정상회원 정보 조회
     *  - IN = 회원ID
     *  - OUT = 회원DTO
     * */
    MemberDTO getNormalMember(Long mbrId);

    /**
     * 02. 정상회원 정보 수정
     *  - IN = 회원ID
     *  - OUT = []
     * */
    void modifyInfo(MemberDTO memberDTO);

    /**
     * 03. 회원가입
     *  - IN = 회원DTO
     *  - OUT = 가입된 회원DTO
     * */
    MemberDTO registerMember(MemberRegisterDTO memberDTO);

    /**
     * 04. 로그인
     *  - IN : [ 이메일, 비밀번호 ]
     *  - OUt : 세션ID
     * */
    void login(HttpSession session, String email, String password) throws SecurityException;

    /**
     * 05. 로그아웃
     *  - IN : []
     *  - OUT : []
     * */
    void logout(HttpSession session);

    /**
     * 06. 회원-운동일지 일치여부 판정
     *  - IN : 회원ID , 운동일지ID
     *  - OUT :
     *    회원-운동일지 일치 : true
     *    회원-운동일지 불일치 : false
     * */
    boolean isJnalOfMember(Long mbrId, Long jnalId);

    /**
     * 07. 회원-그룹 일치여부 판정
     *  - IN : 회원ID , 그룹ID
     *  - OUT :
     *    회원-그룹 일치 : true
     *    회원-그룹 불일치 : false
     * */
    boolean isGrpOfMember(Long mbrId, Long grpId);

    /**
     * Transform Member => MemberDTO
     *  - IN = [Single] Member Entity
     *  - OUT = [Single] Member DTO
     * */
    default MemberDTO entityToDTO(Member entity) {
        return MemberDTO
                .builder()
                .mbrId(entity.getMbrId())
                .mbrNm(entity.getMbrNm())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .statSmsSendYn(entity.getStatSmsSendYn())
                .profImgPath(entity.getProfImgPath())
                .mbrRoleClsfCd(entity.getMbrRoleClsfCd())
                .build();
    }

    /**
     * Transform MemberDTO => Member
     *  - IN = [Single] Member DTO
     *  - OUT = [Single] Member Entity
     * */
    default Member DTOToEntity(MemberDTO memberDTO) {
        return Member
                .builder()
                .mbrId(memberDTO.getMbrId())
                .mbrNm(memberDTO.getMbrNm())
                .email(memberDTO.getEmail())
                .phone(memberDTO.getPhone())
                .statSmsSendYn(memberDTO.getStatSmsSendYn())
                .profImgPath(memberDTO.getProfImgPath())
                .mbrRoleClsfCd(memberDTO.getMbrRoleClsfCd())
                .build();
    }

}
