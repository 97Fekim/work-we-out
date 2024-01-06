package com.fekim.workweout.online;

import com.fekim.workweout.online.date.repository.DateRepository;
import com.fekim.workweout.online.date.repository.entity.Date;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.group.repository.GrpRepository;
import com.fekim.workweout.online.group.repository.MemberGrpRepository;
import com.fekim.workweout.online.group.repository.entity.Grp;
import com.fekim.workweout.online.group.repository.entity.MemberGrp;
import com.fekim.workweout.online.jnal.repository.WkoutJnalMethodRepository;
import com.fekim.workweout.online.jnal.repository.WkoutJnalRepository;
import com.fekim.workweout.online.jnal.repository.WkoutMethodRepository;
import com.fekim.workweout.online.jnal.repository.entity.WkoutJnal;
import com.fekim.workweout.online.jnal.repository.entity.WkoutJnalMethod;
import com.fekim.workweout.online.jnal.repository.entity.WkoutMethod;
import com.fekim.workweout.online.member.repository.MemberRepository;
import com.fekim.workweout.online.member.repository.entity.Member;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntityMappingTest {

    @Autowired
    private DateRepository dateRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private GrpRepository grpRepository;
    @Autowired
    private WkoutMethodRepository wkoutMethodRepository;
    @Autowired
    private MemberGrpRepository memberGrpRepository;
    @Autowired
    private WkoutJnalRepository wkoutJnalRepository;
    @Autowired
    private WkoutJnalMethodRepository wkoutJnalMethodRepository;

    @Test
    void dateInsertPass() {
        dateRepository.save(
                Date.builder()
                        .yyyyMmDd(YyyyMmDd.builder().yyyy("1111").mm("11").dd("11").build())
                        .yyyyMmW(YyyyMmW.builder().cuofYyyy("1111").cuofMm("11").cuofWeek("2").build())
                        .dayOfWeekClsfCd("mon")
                        .holyDayYn("N")
                        .build()
        );
    }

    @Test
    void memberInsertFail() {
        memberRepository.save(Member.builder().build());
    }

    @Test
    void memberInsertPass() {
        memberRepository.save(
                Member.builder()
                        .email("16fekim@gmail.com")
                        .phone("01090374099")
                        .mbrNm("Fekim")
                        .mbrRoleClsfCd("1")
                        .mbrStatClsfCd("1")
                        .statSmsSendYn("N")
                        .build()
        );
    }

    @Test
    void grpInertFail() {
        grpRepository.save(Grp.builder().build());
    }

    @Test
    void grpInsertPass() {
        grpRepository.save(
                Grp.builder()
                        .grpNm("group1")
                        .srtDt("20240105")
                        .build()
        );
    }

    @Test
    void wkoutMethodInsertFail() {
        wkoutMethodRepository.save(WkoutMethod.builder().build());
    }

    @Test
    void wkoutMethodInsertPass() {
        wkoutMethodRepository.save(
                WkoutMethod.builder()
                        .methodNm("벤치프레스")
                        .targetPart("가슴")
                        .build()
        );
    }

    @Test
    void wkoutJnalInsertFail() {

        wkoutJnalRepository.save(
                WkoutJnal.builder()
                        .member(Member.builder().mbrId(999L).build())
                        .yyyyMmDd(YyyyMmDd.builder().yyyy("1111").mm("11").dd("11").build())
                        .comments("comment")
                        .build()
        );
    }

    @Test
    void wkoutJnalInsertPass() {

        wkoutJnalRepository.save(
                WkoutJnal.builder()
                        .member(Member.builder().mbrId(1L).build())
                        .yyyyMmDd(YyyyMmDd.builder().yyyy("1111").mm("11").dd("11").build())
                        .comments("comment")
                        .build()
        );
    }

    @Test
    void wkoutJnalMethodInsertFail() {
        wkoutJnalMethodRepository.save(
                WkoutJnalMethod.builder()
                        .wkoutJnal(WkoutJnal.builder().jnalId(999L).build())
                        .wkoutMethod(WkoutMethod.builder().methodId(999L).build())
                        .build()
        );
    }

    @Test
    void wkoutJnalMethodInsertPass() {
        wkoutJnalMethodRepository.save(
                WkoutJnalMethod.builder()
                        .wkoutJnal(WkoutJnal.builder().jnalId(3L).build())
                        .wkoutMethod(WkoutMethod.builder().methodId(1L).build())
                        .build()
        );
    }

    @Test
    void memberGrpInsertFail() {
        memberGrpRepository.save(MemberGrp.builder().build());
    }

    @Test
    void memberGrpInsertFailPass() {
        memberGrpRepository.save(MemberGrp.builder()
                        .member(Member.builder()
                                .mbrId(2L).build())
                        .grp(Grp.builder()
                                .grpId(2L).build())
                .build()
        );
    }

}
