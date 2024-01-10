package com.fekim.workweout.online.group.service;

import com.fekim.workweout.online.group.repository.entity.Grp;
import com.fekim.workweout.online.group.service.dto.GrpDTO;
import com.fekim.workweout.online.group.service.dto.GrpListDTO;

public interface GrpService {
    
    /** 
     * 01. 내 그룹 리스트 조회.
     *  - IN = 회원번호
     *  - OUT = 회원이 가입한 모든 그룹
     * */
    GrpListDTO getGrpListByMbrId(Long mbrId);

    /**
     * 02 새 그룹 생성
     *  - IN = [그룹명, 회원ID(본인)]
     *  - OUT = 생성된 그룹DTO
     */
    GrpDTO createGrp(String grpNm, Long mbrId);



    /**
     * Transform WkoutMethod  [Single] Entity => [Single] DTO
     * */
    default GrpDTO grpToGrpDTO(Grp entity) {
        return GrpDTO
                .builder()
                .grpId(entity.getGrpId())
                .grpNm(entity.getGrpNm())
                .srtDt(entity.getSrtDt())
                .build();
    }



}
