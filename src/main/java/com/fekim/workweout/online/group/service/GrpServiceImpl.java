package com.fekim.workweout.online.group.service;

import com.fekim.workweout.online.group.repository.GrpRepository;
import com.fekim.workweout.online.group.repository.MemberGrpRepository;
import com.fekim.workweout.online.group.repository.entity.Grp;
import com.fekim.workweout.online.group.service.dto.GrpDTO;
import com.fekim.workweout.online.group.service.dto.GrpListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class GrpServiceImpl implements GrpService {

    private final MemberGrpRepository memberGrpRepository;

    /**
     * 01. 내 그룹 리스트 조회.
     *  - IN = 회원번호
     *  - OUT = 회원이 가입한 모든 그룹
     * */
    @Override
    public GrpListDTO getGrpListByMbrId(Long mbrId) {

        List<Grp> grpList = memberGrpRepository.findGrpListByMbrId(mbrId);
        List<GrpDTO> outList = new ArrayList<>();

        for (Grp g : grpList) {
            outList.add(
                GrpDTO.builder()
                        .grpId(g.getGrpId())
                        .grpNm(g.getGrpNm())
                        .srtDt(g.getSrtDt())
                        .build()
            );
        }

        return GrpListDTO.builder().grpDTOList(outList).build();
    }

}
