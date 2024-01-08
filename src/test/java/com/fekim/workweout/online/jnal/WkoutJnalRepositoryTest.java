package com.fekim.workweout.online.jnal;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.jnal.repository.WkoutJnalRepository;
import com.fekim.workweout.online.jnal.repository.entity.WkoutJnal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WkoutJnalRepositoryTest {

    @Autowired
    private WkoutJnalRepository wkoutJnalRepository;

    @Test
    void noMonthlyCalendarTest() {
        List<Object[]> answer =
                wkoutJnalRepository.findOneMonthCalendarObjects(1L,
                YyyyMm.builder().cuofYyyy("2024").cuofMm("01").build()
        );

        System.out.println("================list out================");
        for (Object[] objects : answer ) {
            System.out.println((String) objects[0]);
            System.out.println((String) objects[1]);
            System.out.println((String) objects[2]);
            System.out.println((String) objects[3]);

            System.out.println();
        }
        System.out.println("================list out================");

    }

    @Test
    void findWkoutJnalsByMbrIdAndYyyyMmDdTest() {

        List<WkoutJnal> answer =  wkoutJnalRepository.findWkoutJnalsByMbrIdAndYyyyMmDd(1L,
                YyyyMmDd.builder().yyyy("2024").mm("01").dd("02").build());

        System.out.println("================list out================");
        for (WkoutJnal w : answer) {
            System.out.println(w.getJnalId());
            System.out.println(w.getYyyyMmDd().getYyyy()+"/"+w.getYyyyMmDd().getMm()+"/"+w.getYyyyMmDd().getDd());
            System.out.println();
        }
        System.out.println("================list out================");

    }

}
