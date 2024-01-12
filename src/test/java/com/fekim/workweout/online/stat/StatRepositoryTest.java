package com.fekim.workweout.online.stat;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.stat.repository.StatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StatRepositoryTest {

    @Autowired
    private StatRepository statRepository;

    @Test
    void findWeeklyMethodTotalSetsTest() {
        List<Object[]> weeklyMethodTotalSets = statRepository.findWeeklyMethodTotalSets(
                1L, YyyyMmW
                        .builder()
                        .cuofYyyy("2024")
                        .cuofMm("01")
                        .cuofWeek("1")
                        .build()
        );

        System.out.println("================list start================");
        for (Object[] objects : weeklyMethodTotalSets) {
            System.out.println((String)objects[0] + " : " + (Long)objects[1]);
            System.out.println();
        }
        System.out.println("================list end================");

    }


}
