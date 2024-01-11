package com.fekim.workweout.online.group;

import com.fekim.workweout.online.group.repository.entity.Grp;
import com.fekim.workweout.online.group.service.GrpService;
import com.fekim.workweout.online.group.service.dto.GrpDTO;
import com.fekim.workweout.online.group.service.dto.GrpListDTO;
import com.fekim.workweout.online.group.service.dto.MemberGrpDTO;
import com.fekim.workweout.online.group.service.dto.OneMonthGrpCalendarDTO;
import com.fekim.workweout.online.jnal.service.dto.OneDayJnalsDTO;
import com.fekim.workweout.online.jnal.service.dto.WkoutJnalMethodDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class GrpServiceTest {

    @Autowired
    private GrpService grpService;

    @Test
    void getGrpListByMbrIdTest() {
        GrpListDTO out = grpService.getGrpListByMbrId(1L);

        System.out.println("===============getGrpListByMbrIdPass===============");
        for (GrpDTO g : out.getGrpDTOList()) {
            System.out.println(g.getGrpId()+" "+g.getGrpNm()+" "+g.getSrtDt());
        }
        System.out.println("===============getGrpListByMbrIdPass===============");


        Assertions.assertThat(out.getGrpDTOList().size() > 0);
    }

    @Test
    @Commit
    void createGrpTest() {
        GrpDTO grpDTO = grpService.createGrp("두번째그룹", 2L);

        System.out.println("================grp start================");
        System.out.println(grpDTO.toString());
        System.out.println("↓↓↓↓ MEMBER_GRP INFO ↓↓↓↓");
        for (MemberGrpDTO memberGrpDTO : grpDTO.getMemberGrpDTOList()) {
            System.out.println(memberGrpDTO.toString());
        }
        System.out.println("================grp end================");
    }

    @Test
    void getGrpInfoTest() {
        GrpDTO grpDTO = grpService.getGrpInfo(1L);

        System.out.println("================grp start================");
        System.out.println(grpDTO.toString());
        System.out.println("↓↓↓↓ MEMBER_GRP INFO ↓↓↓↓");
        for (MemberGrpDTO memberGrpDTO : grpDTO.getMemberGrpDTOList()) {
            System.out.println(memberGrpDTO.toString());
        }
        System.out.println("================grp end================");

    }

    @Test
    void getOneMonthGrpCalendarTest() {

        OneMonthGrpCalendarDTO oneMonthGrpCalendar = grpService.getOneMonthGrpCalendar(2L, "202401");
        System.out.println("================Calendar Start================");
        System.out.println(oneMonthGrpCalendar.toString());
        System.out.println("================Calendar End================");

    }

    @Test
    void getOneDayGrpJnalsTest() {
        OneDayJnalsDTO oneDayGrpJnals = grpService.getOneDayGrpJnals(1L, "20240101");
        System.out.println(oneDayGrpJnals.toString());
    }

}
