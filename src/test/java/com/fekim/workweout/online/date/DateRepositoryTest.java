package com.fekim.workweout.online.date;

import com.fekim.workweout.online.date.repository.DateRepository;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DateRepositoryTest {

    @Autowired
    private DateRepository dateRepository;

    @Test
    void findBeforeCuofYyyyMmWTest() {

        List<Object[]> beforeCuofYyyyMmW = dateRepository.findBeforeCuofYyyyMmW(
                YyyyMmW.builder()
                        .cuofYyyy("2024")
                        .cuofMm("01")
                        .cuofWeek("1")
                        .build(),
                1L
        );

        Assertions.assertThat(String.valueOf(beforeCuofYyyyMmW.get(0)[0]).equals("2023") &&
                String.valueOf(beforeCuofYyyyMmW.get(0)[1]).equals("12") &&
                String.valueOf(beforeCuofYyyyMmW.get(0)[2]).equals("04"));
    }

    @Test
    void findBeforeCuofYyyyMmTest() {

        List<Object[]> beforeCuofYyyyMmW = dateRepository.findBeforeCuofYyyyMm(
                YyyyMm.builder()
                        .cuofYyyy("2024")
                        .cuofMm("01")
                        .build(),
                1L
        );

        Assertions.assertThat(String.valueOf(beforeCuofYyyyMmW.get(0)[0]).equals("2023") &&
                String.valueOf(beforeCuofYyyyMmW.get(0)[1]).equals("12"));
    }

    @Test
    void findBeforeCuofYyyyMmDdTest() {

        List<Object[]> beforeYyyyMmDd = dateRepository.findBeforeYyyyMmDd(
                YyyyMmDd.builder()
                        .yyyy("2023")
                        .mm("12")
                        .dd("01")
                        .build(),
                1L
        );

        Assertions.assertThat(
                beforeYyyyMmDd.get(0)[0].equals("2023") &&
                        beforeYyyyMmDd.get(0)[1].equals("11") &&
                        beforeYyyyMmDd.get(0)[2].equals("30") &&
                        beforeYyyyMmDd.get(0)[3].equals("THU")
                );
    }

    @Test
    void findAfterCuofYyyyMmDdTest() {

        List<Object[]> afterYyyyMmDd = dateRepository.findAfterYyyyMmDd(
                YyyyMmDd.builder()
                        .yyyy("2024")
                        .mm("01")
                        .dd("31")
                        .build(),
                1L
        );

        Assertions.assertThat(
                afterYyyyMmDd.get(0)[0].equals("2023") &&
                        afterYyyyMmDd.get(0)[1].equals("02") &&
                        afterYyyyMmDd.get(0)[2].equals("01") &&
                        afterYyyyMmDd.get(0)[3].equals("THU")
        );


    }

}
