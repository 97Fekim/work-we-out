package com.fekim.workweout.online.group.repository;

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

         List<Grp> grpList = memberGrpRepository.getGrpListByMbrId(1L);

        System.out.println("===============getGrpListByMbrIdPass===============");
        for (Grp g : grpList) {
            System.out.println(g.getGrpId()+" "+g.getGrpNm()+" "+g.getSrtDt());
        }
        System.out.println("===============getGrpListByMbrIdPass===============");

         Assertions.assertThat(grpList.size() > 0);
    }

}
