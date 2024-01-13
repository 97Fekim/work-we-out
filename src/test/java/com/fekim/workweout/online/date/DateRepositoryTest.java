package com.fekim.workweout.online.date;

import com.fekim.workweout.online.date.repository.DateRepository;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
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

        System.out.println(String.valueOf(beforeCuofYyyyMmW.get(0)[0]));
        System.out.println(String.valueOf(beforeCuofYyyyMmW.get(0)[1]));
        System.out.println(String.valueOf(beforeCuofYyyyMmW.get(0)[2]));
    }

}
