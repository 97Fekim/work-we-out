package com.fekim.workweout.online.jnal;

import com.fekim.workweout.online.jnal.service.WkoutJnalService;
import com.fekim.workweout.online.jnal.service.dto.OneDayCalendarDTO;
import com.fekim.workweout.online.jnal.service.dto.OneMonthCalendarDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
