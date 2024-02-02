package com.fekim.workweout.online.stat;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.stat.service.StatService;
import com.fekim.workweout.online.stat.service.dto.*;
import org.assertj.core.api.Assertions;
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

        Assertions.assertThat(partTotalSets != null);

        System.out.println("================list start================");
        for (TargetPartTotalSetDTO dto : partTotalSets.getTargetPartTotalSetDTOList()) {
            System.out.println(dto.getTargetPart()+"/"+dto.getTotalSets());
        }
        System.out.println("================list end================");

    }

    @Test
    void getMonthlyTargetPartTotalSetsTest() {
        TargetPartTotalSetsDTO partTotalSets = statService.getMonthlyTargetPartTotalSets(1L, "202401");

        Assertions.assertThat(partTotalSets != null);

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

        Assertions.assertThat(weeklyMethodWeiIncs != null);

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

        Assertions.assertThat(monthlyMethodWeiIncs != null);

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

        Assertions.assertThat(methodWeekMaxWeisDTO != null);

        System.out.println("==========================================");
        for (MethodWeekMaxWeiDTO methodWeekMaxWeiDTO : methodWeekMaxWeisDTO.getMethodWeekMaxWeiDTOList()) {
            System.out.println(methodWeekMaxWeiDTO.getMethodId()+":"+methodWeekMaxWeiDTO.getMethodNm());
            System.out.println(methodWeekMaxWeiDTO.getWeekMaxWeiDTOList().toString());
            System.out.println("==========================================");
        }

    }

    @Test
    void getMethodMonthMaxWeisTest() {
        MethodMonthMaxWeisDTO methodMonthMaxWeisDTO = statService.getMethodMonthMaxWeis(
                1L,
                "202401",
                4
        );

        Assertions.assertThat(methodMonthMaxWeisDTO != null);

        System.out.println("==========================================");
        for (MethodMonthMaxWeiDTO methodMonthMaxWeiDTO : methodMonthMaxWeisDTO.getMethodMonthMaxWeiDTOList()) {
            System.out.println(methodMonthMaxWeiDTO.getMethodId()+":"+methodMonthMaxWeiDTO.getMethodNm());
            System.out.println(methodMonthMaxWeiDTO.getMonthMaxWeiDTOList().toString());
            System.out.println("==========================================");
        }
    }

    @Test
    void getGrpWkoutDaysCntTest() {
        MbrWkoutDaysCntsDTO mbrWkoutDaysCntsDTO = statService.getWeeklyGrpWkoutDaysCnt(
                1L, "2024011"
        );

        Assertions.assertThat(mbrWkoutDaysCntsDTO != null);

        System.out.println(mbrWkoutDaysCntsDTO.toString());
    }

    @Test
    void getMonthlyGrpWkoutDaysCntTest() {
        MbrWkoutDaysCntsDTO mbrWkoutDaysCntsDTO = statService.getMonthlyGrpWkoutDaysCnt(
                1L, "202401"
        );

        Assertions.assertThat(mbrWkoutDaysCntsDTO != null);

        System.out.println(mbrWkoutDaysCntsDTO.toString());
    }

    @Test
    void getWeeklyGrpTargetPartTotalSetsTest() {
        TargetPartTotalSetsDTO weeklyGrpTargetPartTotalSets = statService.getWeeklyGrpTargetPartTotalSets(
                1L, "2024011"
        );

        Assertions.assertThat(weeklyGrpTargetPartTotalSets != null);

        System.out.println(weeklyGrpTargetPartTotalSets.toString());
    }

    @Test
    void getMonthlyGrpTargetPartTotalSetsTest() {
        TargetPartTotalSetsDTO weeklyGrpTargetPartTotalSets = statService.getMonthlyGrpTargetPartTotalSets(
                1L, "202401"
        );

        Assertions.assertThat(weeklyGrpTargetPartTotalSets != null);

        System.out.println(weeklyGrpTargetPartTotalSets.toString());
    }

    @Test
    void getWeeklyGrpMbrTargetPartTotalSetsTest() {
        GrpMbrTargetPartTotalSetsDTO out = statService.getWeeklyGrpMbrTargetPartTotalSets(1L, "2024011");

        Assertions.assertThat(out != null);

        System.out.println(out.toString());
    }

    @Test
    void getMonthlyGrpMbrTargetPartTotalSetsTest() {
        GrpMbrTargetPartTotalSetsDTO out = statService.getMonthlyGrpMbrTargetPartTotalSets(1L, "202401");

        Assertions.assertThat(out != null);

        System.out.println(out.toString());
    }

    @Test
    void getWeeklySmsSendSuccessFailCntTest() {
        SmsSendSuccessFailCntDTO smsSendSuccessFailCnt = statService.getWeeklySmsSendSuccessFailCnt("2024011");

        Assertions.assertThat(smsSendSuccessFailCnt != null);

        System.out.println(smsSendSuccessFailCnt.toString());

    }

    @Test
    void getMonthlySmsSendSuccessFailCntTest() {
        SmsSendSuccessFailCntDTO smsSendSuccessFailCnt = statService.getMonthlySmsSendSuccessFailCnt("202401");

        Assertions.assertThat(smsSendSuccessFailCnt != null);

        System.out.println(smsSendSuccessFailCnt.toString());

    }

}
