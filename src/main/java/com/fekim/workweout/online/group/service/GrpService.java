package com.fekim.workweout.online.group.service;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.group.repository.entity.Grp;
import com.fekim.workweout.online.group.service.dto.*;
import com.fekim.workweout.online.jnal.service.dto.OneDayCalendarDTO;
import com.fekim.workweout.online.jnal.service.dto.OneDayJnalsDTO;

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
     * 04. 그룹 운동달력 조회.
     *  - IN = 그룹ID , YYYY/MM
     *  - OUT = MM월의 달력(하루동안 작성된 운동일지의 모든 작성자를 포함)
     * */
    OneMonthGrpCalendarDTO getOneMonthGrpCalendar(Long grpId, String yyyyMm);

    /**
     * 05. 그룹 일일 운동일지 리스트 조회.
     *  - IN = 그룹ID , YYYY/MM/DD
     *  - OUT = DD일 작성, 그룹원 모두의 운동일지 리스트 조회. (운동일지 내 모든 운동부위를 포함)
     */
    OneDayJnalsDTO getOneDayGrpJnals(Long grpId, String yyyyMmDd);



    /**
     * Transform Grp  [Single] Entity => [Single] DTO including MemberGrpList
     * */
    default GrpDTO grpToGrpDTO(Grp entity, List<Object[]> memberGrpList) {
        List<MemberGrpDTO> memberGrpDTOList = new ArrayList<>();

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
