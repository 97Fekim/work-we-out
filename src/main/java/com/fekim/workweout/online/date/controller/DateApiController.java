package com.fekim.workweout.online.date.controller;

import com.fekim.workweout.online.date.repository.DateRepository;
import com.fekim.workweout.online.date.repository.entity.Date;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Log4j2
@RestController
@RequestMapping("/date/")
@RequiredArgsConstructor
public class DateApiController {

    private final DateRepository dateRepository;

    /**
     * 01. 기준일의 기준주 조회 (ex 오늘 -> 2023년/1월/4주차)
     * - IN = YYYY/MM/DD
     * - OUT = YYYY/MM/W
     * */
    @GetMapping("/cuof-week")
    ResponseEntity<String> getCuofYyyyMmW(@RequestParam("yyyyMmDd")String yyyyMmDd) {

        String yyyy = yyyyMmDd.substring(0, 4);
        String mm = yyyyMmDd.substring(4,6);
        String dd = yyyyMmDd.substring(6,8);

        Date date = dateRepository.findById(YyyyMmDd.builder()
                .yyyy(yyyy)
                .mm(mm)
                .dd(dd)
                .build()).get();

        String yyyyMmW = date.getYyyyMmW().getCuofYyyy()
                + date.getYyyyMmW().getCuofMm()
                + date.getYyyyMmW().getCuofWeek();

        return new ResponseEntity<>(yyyyMmW, HttpStatus.OK);
    }

    /**
     * 02. 기준일의 기준월 조회 (ex 오늘 -> 2023년/1월차)
     * - IN = YYYY/MM/DD
     * - OUT = YYYY/MM
     * */
    @GetMapping("/cuof-month")
    ResponseEntity<String> getCuofYyyyMm(@RequestParam("yyyyMmDd")String yyyyMmDd) {

        String yyyy = yyyyMmDd.substring(0, 4);
        String mm = yyyyMmDd.substring(4,6);
        String dd = yyyyMmDd.substring(6,8);

        Date date = dateRepository.findById(YyyyMmDd.builder()
                .yyyy(yyyy)
                .mm(mm)
                .dd(dd)
                .build()).get();

        String yyyyMm = date.getYyyyMmW().getCuofYyyy()
                + date.getYyyyMmW().getCuofMm();

        return new ResponseEntity<>(yyyyMm, HttpStatus.OK);
    }

    /**
     * 03. 기준주 +-range 주 조회
     *  - IN = YYYY/MM/W,  range
     *  - OUT = YYYY/MM/W
     * */
    @GetMapping("/new-week")
    ResponseEntity<String> getNewCuofYyyyMmW(@RequestParam("yyyyMmW") String yyyyMmW,
                                         @RequestParam("range") Long range) {

        String yyyy = yyyyMmW.substring(0, 4);
        String mm = yyyyMmW.substring(4,6);
        String week = yyyyMmW.substring(6,7);

        List<Object[]> newYyyyMmWObj = null;

        if (range > 0) {
            newYyyyMmWObj = dateRepository.findAfterCuofYyyyMmW(
                    YyyyMmW.builder()
                            .cuofYyyy(yyyy)
                            .cuofMm(mm)
                            .cuofWeek(week)
                            .build(),
                    range
            );
        } else {
            newYyyyMmWObj = dateRepository.findBeforeCuofYyyyMmW(
                    YyyyMmW.builder()
                            .cuofYyyy(yyyy)
                            .cuofMm(mm)
                            .cuofWeek(week)
                            .build(),
                    (-1) * range
            );
        }

        String newYyyyMmW =
                String.valueOf(newYyyyMmWObj.get(0)[0])
                + String.valueOf(newYyyyMmWObj.get(0)[1])
                + String.valueOf(newYyyyMmWObj.get(0)[2]);

        return new ResponseEntity<>(newYyyyMmW, HttpStatus.OK);
    }

    /**
     * 04. 기준월 +-range 월 조회
     *  - IN = YYYY/MM,  range
     *  - OUT = YYYY/MM
     * */
    @GetMapping("/new-month")
    ResponseEntity<String> getNewCuofYyyyMm(@RequestParam("yyyyMm") String yyyyMm,
                                                @RequestParam("range") Long range) {

        String yyyy = yyyyMm.substring(0, 4);
        String mm = yyyyMm.substring(4,6);

        List<Object[]> newYyyyMmObj = null;

        if (range > 0) {
            newYyyyMmObj = dateRepository.findAfterCuofYyyyMm(
                    YyyyMm.builder()
                            .cuofYyyy(yyyy)
                            .cuofMm(mm)
                            .build(),
                    range
            );
        } else {
            newYyyyMmObj = dateRepository.findBeforeCuofYyyyMm(
                    YyyyMm.builder()
                            .cuofYyyy(yyyy)
                            .cuofMm(mm)
                            .build(),
                    (-1) * range
            );
        }

        String newYyyyMm =
                String.valueOf(newYyyyMmObj.get(0)[0])
                + String.valueOf(newYyyyMmObj.get(0)[1]);

        return new ResponseEntity<>(newYyyyMm, HttpStatus.OK);
    }

}
