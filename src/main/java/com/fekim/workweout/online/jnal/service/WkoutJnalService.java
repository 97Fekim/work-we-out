package com.fekim.workweout.online.jnal.service;

import com.fekim.workweout.online.jnal.service.dto.OneDayJnalsDTO;
import com.fekim.workweout.online.jnal.service.dto.OneMonthCalendarDTO;
import com.fekim.workweout.online.jnal.service.dto.WkoutJnalDTO;
import com.fekim.workweout.online.jnal.service.dto.WkoutJnalMethodDTO;

import java.util.ArrayList;
import java.util.List;

public interface WkoutJnalService {

    /**
     * 01. 개인 운동달력 조회.
     *  - IN = 회원번호 , YYYY/MM
     *  - OUT = N월의 달력(운동일지 내 모든 운동부위를 포함)
     * */
    OneMonthCalendarDTO getOneMonthCalendar(Long mbrId, String yyyyMm);

    /**
     * 02. 개인 일일 운동일지 리스트 조회.
     *  - IN = 회원번호 , YYYY/MM/DD
     *  - OUT = DD일의 운동일지 리스트 조회. (운동일지 내 모든 운동부위를 포함)
     */
    OneDayJnalsDTO getOneDayJnals(Long mbrId, String yyyyMmDd);

    /**
     * 03. 개인 운동일지 조회.(PK)
     *  - IN = 운동일지ID
     *  - OUT = 운동일지 1건 조회. (운동일지 내 모든 운동부위를 포함)
     */
    WkoutJnalDTO getOneJnal(Long jnalId);


    /**
     * Transform WkoutJnalMethod
     *  - IN = [List] Complex WkoutJnalMethod Entity
     *  - OUT = [List] WkoutJnalMethod DTO
     * */
    default List<WkoutJnalMethodDTO> jnalMethodsTojnalMethodDTOs(List<Object[]> entity) {
        List<WkoutJnalMethodDTO> wkoutJnalMethodDTOList = new ArrayList<>();

        for (Object[] jnalMethods : entity) {
            wkoutJnalMethodDTOList.add(
                    WkoutJnalMethodDTO
                            .builder()
                            .methodNm((String) jnalMethods[0])
                            .targetPart((String) jnalMethods[1])
                            .weight((Long) jnalMethods[2])
                            .sets((Long) jnalMethods[3])
                            .reps((Long) jnalMethods[4])
                            .restTime((Long) jnalMethods[5])
                            .build()
            );
        }

        return wkoutJnalMethodDTOList;
    }

}
