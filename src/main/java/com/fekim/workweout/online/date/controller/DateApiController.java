package com.fekim.workweout.online.date.controller;

import com.fekim.workweout.online.date.repository.DateRepository;
import com.fekim.workweout.online.date.repository.entity.Date;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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


}
