package com.fekim.workweout.online.group.service;

import com.fekim.workweout.online.group.repository.entity.Grp;
import com.fekim.workweout.online.group.repository.entity.MemberGrp;
import com.fekim.workweout.online.group.service.dto.GrpDTO;
import com.fekim.workweout.online.group.service.dto.GrpListDTO;
import com.fekim.workweout.online.group.service.dto.MemberGrpDTO;
import com.fekim.workweout.online.member.service.dto.MemberDTO;

import java.util.ArrayList;
import java.util.List;

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
     * 03. 그룹 정보 조회
     *  - IN = 그룹ID
     *  - OUT = 회원DTO 리스트를 포함한 그룹DTO
     * */
    GrpDTO getGrpInfo(Long grpId);

    /**
     * Transform WkoutMethod  [Single] Entity => [Single] DTO including MemberGrpList
     * */
    default GrpDTO grpToGrpDTO(Grp entity, List<Object[]> memberGrpList) {
        List<MemberGrpDTO> memberGrpDTOList = new ArrayList<MemberGrpDTO>();

        for (Object[] memberGrp : memberGrpList) {
            memberGrpDTOList.add(
                    MemberGrpDTO
                            .builder()
                            .mbrGrpId((Long) memberGrp[0])
                            .mbrId((Long) memberGrp[1])
                            .mbrNm((String) memberGrp[2])
                            .profImgPath((String) memberGrp[3])
                            .build()
            );
        }

        return GrpDTO
                .builder()
                .grpId(entity.getGrpId())
                .grpNm(entity.getGrpNm())
                .srtDt(entity.getSrtDt())
                .memberGrpDTOList(memberGrpDTOList)
                .build();
    }

}
