package com.fekim.workweout.online.jnal;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.jnal.repository.WkoutJnalMethodRepository;
import com.fekim.workweout.online.jnal.repository.entity.WkoutJnalMethod;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
public class WkoutJnalMethodRepositoryTest {

    @Autowired
    private WkoutJnalMethodRepository wkoutJnalMethodRepository;

    @Test
    void findWkoutJnalMethodsByJnalIdTest() {
        List<Object[]> answer = wkoutJnalMethodRepository.findAllByJnalId(1L);

        System.out.println("================list out================");
        for (Object[] w : answer) {
            System.out.println(w.length);
        }
        System.out.println("================list out================");

    }

    @Transactional
    @Test
    void deleteByJnalIdTest() {
        wkoutJnalMethodRepository.deleteByJnalId(4L);
    }

}
