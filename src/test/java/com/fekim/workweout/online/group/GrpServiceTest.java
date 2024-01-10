package com.fekim.workweout.online.group;

import com.fekim.workweout.online.group.repository.entity.Grp;
import com.fekim.workweout.online.group.service.GrpService;
import com.fekim.workweout.online.group.service.dto.GrpDTO;
import com.fekim.workweout.online.group.service.dto.GrpListDTO;
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
        grpService.createGrp("첫번째그룹", 1L);
    }

}
