package com.fekim.workweout.online.group.service;

import com.fekim.workweout.online.date.repository.DateRepository;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.group.repository.GrpRepository;
import com.fekim.workweout.online.group.repository.MemberGrpRepository;
import com.fekim.workweout.online.group.repository.entity.Grp;
import com.fekim.workweout.online.group.repository.entity.MemberGrp;
import com.fekim.workweout.online.group.service.dto.*;
import com.fekim.workweout.online.jnal.repository.WkoutJnalRepository;
import com.fekim.workweout.online.jnal.service.WkoutJnalService;
import com.fekim.workweout.online.jnal.service.dto.OneDayCalendarDTO;
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
    private final DateRepository dateRepository;


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

    /**
     * Transform Grp Calendar  [List] Entity => [Single] OneMonthGrpCalendar DTO
     * */
    private OneMonthGrpCalendarDTO makeOneMonthCalendarDTO(List<Object[]> entities, Long grpId) {

        OneMonthGrpCalendarDTO oneMonthGrpCalendarDTO = OneMonthGrpCalendarDTO.builder().grpId(grpId).build();

        for (Object[] oneDay : entities) {

            // 초기 바인딩변수 선언한다.
            String yyyy = (String) oneDay[0];
            String mm = (String) oneDay[1];
            String dd = (String) oneDay[2];
            String dayOfClsfCd = (String) oneDay[3];
            List<MemberGrpDTO> memberGrpDTOList = new ArrayList<>();

            // 일기가 존재하는 날짜인 경우에만 설정한다.
            if (oneDay[4] != null) {

                String[] mbrGrps = ((String) oneDay[4]).split(",");

                for (String member : mbrGrps) {
                    String[] mbrInfo = member.split("/");

                    Long mbrGrpId = Long.parseLong(mbrInfo[0]);
                    Long mbrId = Long.parseLong(mbrInfo[1]);
                    String mbrNm = mbrInfo[2];
                    String profImgPath = mbrInfo[3];

                    memberGrpDTOList.add(MemberGrpDTO
                            .builder()
                            .mbrGrpId(mbrGrpId)
                            .mbrId(mbrId)
                            .mbrNm(mbrNm)
                            .profImgPath(profImgPath)
                            .build());
                }
            }

            // 최종 바인딩한다.
            OneDayGrpCalendarDTO oneDayGrpCalendarDTO = new OneDayGrpCalendarDTO();
            oneDayGrpCalendarDTO.setYyyy(yyyy);
            oneDayGrpCalendarDTO.setMm(mm);
            oneDayGrpCalendarDTO.setDd(dd);
            oneDayGrpCalendarDTO.setDayOfWeekClsfCd(dayOfClsfCd);
            oneDayGrpCalendarDTO.setMemberGrpDTOList(memberGrpDTOList);

            oneMonthGrpCalendarDTO.getOneDayGrpCalendarDTOList()
                    .add(oneDayGrpCalendarDTO);
        }

        List<OneDayGrpCalendarDTO> fixedList = oneMonthGrpCalendarDTO.getOneDayGrpCalendarDTOList();

        // 전월 DUMMY DAY PADDING
        //  - 1일이 일요일이 아닌경우
        if (!fixedList.get(0).getDayOfWeekClsfCd().equals("SUN")) {

            List<Object[]> beforeYyyyMmDd = dateRepository.findBeforeYyyyMmDd(
                    YyyyMmDd
                            .builder()
                            .yyyy(fixedList.get(0).getYyyy())
                            .mm(fixedList.get(0).getMm())
                            .dd(fixedList.get(0).getDd())
                            .build(),
                    1L
            );

            // 직전일부터 시작
            OneDayGrpCalendarDTO oneDayGrpCalendarDTO = OneDayGrpCalendarDTO
                    .builder()
                    .yyyy(String.valueOf(beforeYyyyMmDd.get(0)[0]))
                    .mm(String.valueOf(beforeYyyyMmDd.get(0)[1]))
                    .dd(String.valueOf(beforeYyyyMmDd.get(0)[2]))
                    .dayOfWeekClsfCd(String.valueOf(beforeYyyyMmDd.get(0)[3]))
                    .build();

            // 토요일을 만나면 멈춘다. (일요일까지만 PADDING 한다.)
            while (!oneDayGrpCalendarDTO.getDayOfWeekClsfCd().equals("SAT")) {

                // 제일 왼쪽에 PADDING한다.
                fixedList.add(0, oneDayGrpCalendarDTO);

                // -1일 한다.
                List<Object[]> prevYyyyMmDd = dateRepository.findBeforeYyyyMmDd(
                        YyyyMmDd
                                .builder()
                                .yyyy(oneDayGrpCalendarDTO.getYyyy())
                                .mm(oneDayGrpCalendarDTO.getMm())
                                .dd(oneDayGrpCalendarDTO.getDd())
                                .build(),
                        1L
                );
                oneDayGrpCalendarDTO = new OneDayGrpCalendarDTO();
                oneDayGrpCalendarDTO.setYyyy(String.valueOf(prevYyyyMmDd.get(0)[0]));
                oneDayGrpCalendarDTO.setMm(String.valueOf(prevYyyyMmDd.get(0)[1]));
                oneDayGrpCalendarDTO.setDd(String.valueOf(prevYyyyMmDd.get(0)[2]));
                oneDayGrpCalendarDTO.setDayOfWeekClsfCd(String.valueOf(prevYyyyMmDd.get(0)[3]));
            }
        }

        // 익월 DUMMY DAY PADDING
        //  - 말일이 토요일이 아닌경우
        if (!fixedList.get(fixedList.size()-1)
                .getDayOfWeekClsfCd().equals("SAT")) {

            List<Object[]> afterYyyyMmDd = dateRepository.findAfterYyyyMmDd(
                    YyyyMmDd
                            .builder()
                            .yyyy(fixedList.get(fixedList.size()-1).getYyyy())
                            .mm(fixedList.get(fixedList.size()-1).getMm())
                            .dd(fixedList.get(fixedList.size()-1).getDd())
                            .build(),
                    1L
            );

            // 익일부터 시작
            OneDayGrpCalendarDTO oneDayGrpCalendarDTO = OneDayGrpCalendarDTO
                    .builder()
                    .yyyy(String.valueOf(afterYyyyMmDd.get(0)[0]))
                    .mm(String.valueOf(afterYyyyMmDd.get(0)[1]))
                    .dd(String.valueOf(afterYyyyMmDd.get(0)[2]))
                    .dayOfWeekClsfCd(String.valueOf(afterYyyyMmDd.get(0)[3]))
                    .build();

            // 일요일을 만나면 멈춘다. (토요일까지만 PADDING 한다.)
            while (!oneDayGrpCalendarDTO.getDayOfWeekClsfCd().equals("SUN")) {

                // 제일 오른쪽에 PADDING한다.
                fixedList.add(oneDayGrpCalendarDTO);

                // -1일 한다.
                List<Object[]> nextYyyyMmDd = dateRepository.findAfterYyyyMmDd(
                        YyyyMmDd
                                .builder()
                                .yyyy(oneDayGrpCalendarDTO.getYyyy())
                                .mm(oneDayGrpCalendarDTO.getMm())
                                .dd(oneDayGrpCalendarDTO.getDd())
                                .build(),
                        1L
                );
                oneDayGrpCalendarDTO = new OneDayGrpCalendarDTO();
                oneDayGrpCalendarDTO.setYyyy(String.valueOf(nextYyyyMmDd.get(0)[0]));
                oneDayGrpCalendarDTO.setMm(String.valueOf(nextYyyyMmDd.get(0)[1]));
                oneDayGrpCalendarDTO.setDd(String.valueOf(nextYyyyMmDd.get(0)[2]));
                oneDayGrpCalendarDTO.setDayOfWeekClsfCd(String.valueOf(nextYyyyMmDd.get(0)[3]));
            }
        }

        return oneMonthGrpCalendarDTO;

    }

}
