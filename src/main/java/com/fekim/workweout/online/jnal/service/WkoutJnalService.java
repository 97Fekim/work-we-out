package com.fekim.workweout.online.jnal.service;

import com.fekim.workweout.online.jnal.service.dto.OneDayJnalsDTO;
import com.fekim.workweout.online.jnal.service.dto.OneMonthCalendarDTO;

public interface WkoutJnalService {

    /**
     * 01. 개인 운동달력 조회.
     *  - IN = 회원번호 , YYYY/MM
     *  - OUT = N월의 달력(운동일지 내 모든 운동부위를 포함)
     * */
    OneMonthCalendarDTO getOneMonthCalendar(Long mbrId, String yyyyMm);

    /**
     * 02. 개인 운동일지 조회.
     *  - IN = 회원번호 , YYYY/MM/DD
     *  - OUT = DD일의 운동일지 조회. (운동일지 내 모든 운동부위를 포함)
     */
    OneDayJnalsDTO getOneDayJnals(Long mbrId, String yyyyMmDd);

}
