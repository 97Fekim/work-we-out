package com.fekim.workweout.online.stat;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.stat.service.StatService;
import com.fekim.workweout.online.stat.service.dto.*;
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

    @Test
    void getWeeklyMethodWeiIncsTest() {
        MethodWeiIncsDTO weeklyMethodWeiIncs = statService.getWeeklyMethodWeiIncs(
                1L,
                "2024011",
                "2024012"
        );
        System.out.println("================list start================");
        for (MethodWeiIncDTO methodWeiIncDTO : weeklyMethodWeiIncs.getMethodWeiIncDTOList()) {
            System.out.println(methodWeiIncDTO.toString());
        }
        System.out.println("================list end================");

    }

    @Test
    void getMonthlyMethodWeiIncsTest() {
        MethodWeiIncsDTO monthlyMethodWeiIncs = statService.getMonthlyMethodWeiIncs(
                1L,
                "202401",
                "202402"
        );
        System.out.println("================list start================");
        for (MethodWeiIncDTO methodWeiIncDTO : monthlyMethodWeiIncs.getMethodWeiIncDTOList()) {
            System.out.println(methodWeiIncDTO.toString());
        }
        System.out.println("================list end================");

    }

    @Test
    void getMethodWeekMaxWeisTest() {
        MethodWeekMaxWeisDTO methodWeekMaxWeisDTO = statService.getMethodWeekMaxWeis(
                1L,
                "2024012",
                4
        );

        System.out.println("==========================================");
        for (MethodWeekMaxWeiDTO methodWeekMaxWeiDTO : methodWeekMaxWeisDTO.getMethodWeekMaxWeiDTOList()) {
            System.out.println(methodWeekMaxWeiDTO.getMethodId()+":"+methodWeekMaxWeiDTO.getMethodNm());
            System.out.println(methodWeekMaxWeiDTO.getWeekMaxWeiDTOList().toString());
            System.out.println("==========================================");
        }

    }

}
