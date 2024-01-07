package com.fekim.workweout.online.group;

import com.fekim.workweout.online.group.repository.MemberGrpRepository;
import com.fekim.workweout.online.group.repository.entity.Grp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GrpRepositoryTest {

    @Autowired
    private MemberGrpRepository memberGrpRepository;

    @Test
    void getGrpListByMbrIdPass() {

         List<Grp> grpList = memberGrpRepository.findGrpListByMbrId(1L);

        System.out.println("===============findGrpListByMbrIdPass===============");
        for (Grp g : grpList) {
            System.out.println(g.getGrpId()+" "+g.getGrpNm()+" "+g.getSrtDt());
        }
        System.out.println("===============findGrpListByMbrIdPass===============");

         Assertions.assertThat(grpList.size() > 0);
    }

}
