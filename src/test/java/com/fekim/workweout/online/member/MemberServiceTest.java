package com.fekim.workweout.online.member;

import com.fekim.workweout.online.member.service.MemberService;
import com.fekim.workweout.online.member.service.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void getNormalMemberTest() {
        System.out.println("================Member Info start================");
        System.out.println(memberService.getNormalMember(5L).toString());
        System.out.println("================Member Info End================");
    }

    @Test
    void modifyInfoTest() {

        memberService.modifyInfo(
                MemberDTO.builder()
                        .mbrId(5L)
                        .mbrNm("Fekim5")
                        .statSmsSendYn("Y")
                        .phone("01090374115")
                        .email("16fekim@gmail.com15")
                        .build()
        );

    }

}
