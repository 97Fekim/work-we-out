package com.fekim.workweout.online.jnal;

import com.fekim.workweout.online.jnal.service.WkoutJnalService;
import com.fekim.workweout.online.jnal.service.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class WkoutJnalServiceTest {

    @Autowired
    private WkoutJnalService wkoutJnalService;

    @Test
    void getOneMonthCalendarTest() {
        OneMonthCalendarDTO oneMonthCalendarDTO =
                wkoutJnalService.getOneMonthCalendar(1L, "202401");

        System.out.println("================list out================");
        for (OneDayCalendarDTO dto : oneMonthCalendarDTO.getOneDayCalendarDTOList()) {
            System.out.println(dto.getYyyy()+"/"+dto.getMm()+"/"+dto.getDd() + " : " +
                    dto.getParts().toString());
        }
        System.out.println("================list out================");

    }
    
    @Test
    void getOneDayJnalsTest() {

        OneDayJnalsDTO oneDayJnals = wkoutJnalService.getOneDayJnals(1L, "20240103");

        System.out.println("================list start================");
        System.out.println();
        for (WkoutJnalDTO jnalDTO : oneDayJnals.getWkoutJnalDTO()) {
            System.out.println("================jnal start================");
            System.out.println(jnalDTO.getYyyy()+"/"+jnalDTO.getMm()+""+jnalDTO.getDd());
            for (WkoutJnalMethodDTO jnalMethodDTO : jnalDTO.getWkoutJnalMethodDTOList()) {
                System.out.println(jnalMethodDTO.toString());
            }
            System.out.println("================jnal end================");
        }
        System.out.println();
        System.out.println("================list end================");
        
    }

    @Test
    void getOneJnalTest() {

        WkoutJnalDTO oneJnal = wkoutJnalService.getOneJnal(1L);

        System.out.println("================jnal start================");
        System.out.println(oneJnal.toString());
        System.out.println(oneJnal.getWkoutJnalMethodDTOList().size());
        System.out.println("================jnal end================");

    }

    @Test
    void saveJnalTest() {

        List<WkoutJnalMethodDTO> wkoutJnalMethodDTOList = new ArrayList<>();

        wkoutJnalMethodDTOList.add(
                WkoutJnalMethodDTO.builder()
                        .methodNm("")
                        .methodId(1L)
                        .build()
        );

        wkoutJnalMethodDTOList.add(
                WkoutJnalMethodDTO.builder()
                        .methodNm("")
                        .methodId(2L)
                        .build()
        );

        WkoutJnalDTO wkoutJnalDTO = WkoutJnalDTO
                .builder()
                .yyyy("2024")
                .mm("01")
                .dd("15")
                .comments("")
                .wkoutJnalMethodDTOList(wkoutJnalMethodDTOList)
                .build();

        wkoutJnalService.saveJnal(wkoutJnalDTO, 1L);
    }

    @Test
    @Commit
    void removeJnalTest() {
        wkoutJnalService.removeJnal(26L);
    }

    @Test
    @Commit
    void modifyJnalTest() {

    }

}
