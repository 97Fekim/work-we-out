package com.fekim.workweout.online.member.service;

import com.fekim.workweout.online.member.repository.MemberRepository;
import com.fekim.workweout.online.member.repository.entity.Member;
import com.fekim.workweout.online.member.service.dto.MemberDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

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
}
