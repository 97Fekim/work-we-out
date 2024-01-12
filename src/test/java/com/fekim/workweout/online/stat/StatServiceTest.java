package com.fekim.workweout.online.stat;

import com.fekim.workweout.online.stat.service.StatService;
import com.fekim.workweout.online.stat.service.dto.TargetPartTotalSetDTO;
import com.fekim.workweout.online.stat.service.dto.TargetPartTotalSetsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StatServiceTest {

    @Autowired
    private StatService statService;

    @Test
    void getWeeklyTargetPartTotalSetsTest() {
        TargetPartTotalSetsDTO partTotalSets = statService.getWeeklyTargetPartTotalSets(1L, "2024011");

        System.out.println("================list start================");
        for (TargetPartTotalSetDTO dto : partTotalSets.getTargetPartTotalSetDTOList()) {
            System.out.println(dto.getTargetPart()+"/"+dto.getTotalSets());
        }
        System.out.println("================list end================");

    }

    @Test
    void getMonthlyTargetPartTotalSetsTest() {
        TargetPartTotalSetsDTO partTotalSets = statService.getMonthlyTargetPartTotalSets(1L, "202401");
        System.out.println("================list start================");
        for (TargetPartTotalSetDTO dto : partTotalSets.getTargetPartTotalSetDTOList()) {
            System.out.println(dto.getTargetPart()+"/"+dto.getTotalSets());
        }
        System.out.println("================list end================");
    }

}
