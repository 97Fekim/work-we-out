package com.fekim.workweout.online.jnal;

import com.fekim.workweout.online.jnal.repository.WkoutJnalMethodRepository;
import com.fekim.workweout.online.jnal.repository.entity.WkoutJnalMethod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WkoutJnalMethodRepositoryTest {

    @Autowired
    private WkoutJnalMethodRepository wkoutJnalMethodRepository;

    @Test
    void findWkoutJnalMethodsByJnalIdTest() {
        List<Object[]> answer = wkoutJnalMethodRepository.findWkoutJnalMethodsByJnalId(1L);

        System.out.println("================list out================");
        for (Object[] w : answer) {
            System.out.println(w.length);
        }
        System.out.println("================list out================");

    }

}
