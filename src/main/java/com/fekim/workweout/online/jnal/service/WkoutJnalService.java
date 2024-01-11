package com.fekim.workweout.online.jnal.service;

import com.fekim.workweout.online.jnal.repository.entity.WkoutMethod;
import com.fekim.workweout.online.jnal.service.dto.*;

import java.util.ArrayList;
import java.util.List;

public interface WkoutJnalService {

    /**
     * 00. 전체 운동종목 조회
     *  - IN = []
     *  - OUT = 전체 운동종목
     * */
    WkoutMethodListDTO getAllWkoutMethod();

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
    OneDayJnalsDTO getOneDayJnals(Long Id, String yyyyMmDd, String mbrGrpClsfCd);

    /**
     * 03. 개인 운동일지 조회.(PK)
     *  - IN = 운동일지ID
     *  - OUT = 운동일지 1건 조회. (운동일지 내 모든 운동부위를 포함)
     */
    WkoutJnalDTO getOneJnal(Long jnalId);

    /**
     * 04. 개인 운동일지 저장.
     *  - IN = [운동일지 DTO, 회원번호]
     *  - OUT = 저장한 운동일지ID
     */
    Long createJnal(WkoutJnalDTO wkoutJnalDTO, Long mbrId);

    /**
     * 05. 개인 운동일지 삭제 (운동종목 포함)
     *  - IN = 저널ID
     *  - OUT = []
     * */
    void removeJnal(Long jnalId);

    /**
     * 06. 개인 운동일지 편집
     *  - IN = 운동일지DTO
     *  - OUT = 편집된 운동일지ID
     * */
    Long modifyJnal(WkoutJnalDTO wkoutJnalDTO);


    /**
     * Transform WkoutMethod  [Single] Entity => [Single] DTO
     * */
    default WkoutMethodDTO methodToMethodDTO(WkoutMethod entity) {
        return WkoutMethodDTO.builder()
                .methodId(entity.getMethodId())
                .methodNm(entity.getMethodNm())
                .targetPart(entity.getTargetPart())
                .build();
    }

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
                            .jnalMethodId((Long) jnalMethods[0])
                            .methodId((Long) jnalMethods[1])
                            .methodNm((String) jnalMethods[2])
                            .targetPart((String) jnalMethods[3])
                            .weight((Long) jnalMethods[4])
                            .sets((Long) jnalMethods[5])
                            .reps((Long) jnalMethods[6])
                            .restTime((Long) jnalMethods[7])
                            .build()
            );
        }

        return wkoutJnalMethodDTOList;
    }

}
