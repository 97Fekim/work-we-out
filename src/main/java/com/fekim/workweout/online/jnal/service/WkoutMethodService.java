package com.fekim.workweout.online.jnal.service;

import com.fekim.workweout.online.jnal.repository.entity.WkoutMethod;
import com.fekim.workweout.online.jnal.service.dto.WkoutMethodDTO;
import com.fekim.workweout.online.jnal.service.dto.WkoutMethodListDTO;

public interface WkoutMethodService {

    /**
     * 01. 전체 운동종목 조회
     *  - IN = []
     *  - OUT = 전체 운동종목
     * */
    WkoutMethodListDTO getAllWkoutMethod();

    /**
     * Transform  [Single] Entity => [Single] DTO
     * */
    default WkoutMethodDTO methodToMethodDTO(WkoutMethod entity) {
        return WkoutMethodDTO.builder()
                .methodId(entity.getMethodId())
                .methodNm(entity.getMethodNm())
                .targetPart(entity.getTargetPart())
                .build();
    }

}
