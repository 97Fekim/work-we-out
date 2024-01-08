package com.fekim.workweout.online.jnal.service;

import com.fekim.workweout.online.jnal.service.dto.OneDayJnalsDTO;
import com.fekim.workweout.online.jnal.service.dto.OneMonthCalendarDTO;
import com.fekim.workweout.online.jnal.service.dto.WkoutJnalMethodDTO;

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



    /**
     * Transform WkoutJnalMethod  [Single] Complex Entity => [Single] DTO
     * */
    default WkoutJnalMethodDTO jnalMethodTojnalMethodDTO(Object[] entity) {
        return WkoutJnalMethodDTO.builder()
                .methodNm((String) entity[0])
                .targetPart((String) entity[1])
                .weight((Long) entity[2])
                .sets((Long) entity[3])
                .reps((Long) entity[4])
                .restTime((Long) entity[5])
                .build();
    }
}
