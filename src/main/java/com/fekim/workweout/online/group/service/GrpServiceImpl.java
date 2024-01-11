package com.fekim.workweout.online.group.service;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.group.repository.GrpRepository;
import com.fekim.workweout.online.group.repository.MemberGrpRepository;
import com.fekim.workweout.online.group.repository.entity.Grp;
import com.fekim.workweout.online.group.repository.entity.MemberGrp;
import com.fekim.workweout.online.group.service.dto.GrpDTO;
import com.fekim.workweout.online.group.service.dto.GrpListDTO;
import com.fekim.workweout.online.group.service.dto.OneMonthGrpCalendarDTO;
import com.fekim.workweout.online.jnal.repository.WkoutJnalRepository;
import com.fekim.workweout.online.jnal.service.WkoutJnalService;
import com.fekim.workweout.online.jnal.service.dto.OneDayJnalsDTO;
import com.fekim.workweout.online.member.repository.entity.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class GrpServiceImpl implements GrpService {

    private final MemberGrpRepository memberGrpRepository;
    private final GrpRepository grpRepository;
    private final WkoutJnalRepository wkoutJnalRepository;
    private final WkoutJnalService wkoutJnalService;

    /**
     * 01. 내 그룹 리스트 조회.
     *  - IN = 회원번호
     *  - OUT = 회원이 가입한 모든 그룹
     * */
    @Override
    public GrpListDTO getGrpListByMbrId(Long mbrId) {

        List<Grp> grpList = memberGrpRepository.findGrpListByMbrId(mbrId);
        List<GrpDTO> outList = new ArrayList<>();

        for (Grp grp : grpList) {
            outList.add(
                    grpToGrpDTO(grp, new ArrayList<>())  // 빈 MemberGrp 전달
            );
        }

        return GrpListDTO.builder().grpDTOList(outList).build();
    }

    /**
     * 02 새 그룹 생성
     *  - IN = [그룹명, 회원ID(본인)]
     *  - OUT = 생성된 그룹ID
     */
    @Override
    @Transactional
    public GrpDTO createGrp(String grpNm, Long mbrId) {

        /* (1) Grp 생성 */
        //  (1)-1 현재시간 조회
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        String yyyyMmDd = simpleDateFormat.format(calendar.getTime());

        Grp saved = grpRepository.save(Grp
                .builder()
                .grpNm(grpNm)
                .srtDt(yyyyMmDd)  // 현재시간
                .build()
        );

        /* (2) 본인의 Grp 가입 */
        memberGrpRepository.save(MemberGrp
                .builder()
                .grp(saved)
                .member(Member
                        .builder()
                        .mbrId(mbrId)
                        .build())
                .joinedDt(yyyyMmDd)
                .build());

        /* (3) 본인의 정보도 포함시켜 return */
        List<Object[]> myInfo = memberGrpRepository.findMemberGrpsByGrpId(saved.getGrpId());

        return grpToGrpDTO(saved, myInfo);
    }

    /**
     * 03. 그룹 정보 조회
     *  - IN = 그룹ID
     *  - OUT = 회원DTO 리스트를 포함한 그룹DTO
     * */
    @Override
    public GrpDTO getGrpInfo(Long grpId) {
        Grp grp = grpRepository.findById(grpId).get();

        List<Object[]> memberGrpList = memberGrpRepository.findMemberGrpsByGrpId(grp.getGrpId());

        return grpToGrpDTO(grp, memberGrpList);
    }

    /**
     * 04. 그룹 운동달력 조회.
     *  - IN = 그룹ID , YYYY/MM
     *  - OUT = MM월의 달력(하루동안 작성된 운동일지의 모든 작성자를 포함)
     * */
    @Override
    public OneMonthGrpCalendarDTO getOneMonthGrpCalendar(Long grpId, String yyyyMm) {

        String yyyy = yyyyMm.substring(0, 4);
        String mm = yyyyMm.substring(4,6);

        List<Object[]> oneMonthCalendarEntities = wkoutJnalRepository.findOneMonthGrpCalendarObject
                (grpId, YyyyMm.builder().cuofYyyy(yyyy).cuofMm(mm).build());

        return makeOneMonthCalendarDTO(oneMonthCalendarEntities, grpId);

    }

    /**
     * 05. 그룹 일일 운동일지 리스트 조회.
     *  - IN = 그룹ID , YYYY/MM/DD
     *  - OUT = DD일 작성, 그룹원 모두의 운동일지 리스트 조회. (운동일지 내 모든 운동부위를 포함)
     */
    @Override
    public OneDayJnalsDTO getOneDayGrpJnals(Long grpId, String yyyyMmDd) {
        return wkoutJnalService.getOneDayJnals(grpId, yyyyMmDd, "02");
    }

}
