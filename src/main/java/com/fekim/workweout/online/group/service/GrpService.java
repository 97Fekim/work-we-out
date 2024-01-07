package com.fekim.workweout.online.group.service;

import com.fekim.workweout.online.group.service.dto.GrpListDTO;

public interface GrpService {
    
    /** 
     * 01. 내 그룹 리스트 조회.
     *  - IN = 회원번호
     *  - OUT = 회원이 가입한 모든 그룹
     * */
    GrpListDTO getGrpListByMbrId(Long mbrId);

}
