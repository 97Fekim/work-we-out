package com.fekim.workweout.online.jnal.service;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.jnal.repository.WkoutJnalMethodRepository;
import com.fekim.workweout.online.jnal.repository.WkoutJnalRepository;
import com.fekim.workweout.online.jnal.repository.WkoutMethodRepository;
import com.fekim.workweout.online.jnal.repository.entity.WkoutJnal;
import com.fekim.workweout.online.jnal.repository.entity.WkoutJnalMethod;
import com.fekim.workweout.online.jnal.repository.entity.WkoutMethod;
import com.fekim.workweout.online.jnal.service.dto.*;
import com.fekim.workweout.online.member.repository.entity.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class WkoutJnalServiceImpl implements WkoutJnalService {


    private final WkoutJnalRepository wkoutJnalRepository;
    private final WkoutJnalMethodRepository wkoutJnalMethodRepository;
    private final WkoutMethodRepository wkoutMethodRepository;

    /**
     * 00. 전체 운동종목 조회
     *  - IN = []
     *  - OUT = 전체 운동종목
     * */
    @Override
    public WkoutMethodListDTO getAllWkoutMethod() {

        List<WkoutMethod> wkoutMethodList = wkoutMethodRepository.findAll();

        List<WkoutMethodDTO> wkoutMethodDTOList = new ArrayList<>();
        for (WkoutMethod wkoutMethod : wkoutMethodList) {
            wkoutMethodDTOList.add(methodToMethodDTO(wkoutMethod));
        }

        return WkoutMethodListDTO.builder().wkoutMethodDTOList(wkoutMethodDTOList).build();
    }

    /**
     * 01. 개인 운동 달력 조회.
     *  - IN = 회원번호 , YYYY/MM
     *  - OUT = N월의 달력(운동일지 내 모든 운동부위를 포함)
     * */
    @Override
    public OneMonthCalendarDTO getOneMonthCalendar(Long mbrId, String yyyyMm) {

        String yyyy = yyyyMm.substring(0, 4);
        String mm = yyyyMm.substring(4,6);

        List<Object[]> entities = wkoutJnalRepository.findOneMonthCalendarObjects(
                mbrId,
                YyyyMm.builder().cuofYyyy(yyyy).cuofMm(mm).build()
        );

        List<OneDayCalendarDTO> oneDayCalendarDTOList = new ArrayList<>();

        for (Object[] objects : entities) {

            OneDayCalendarDTO oneDayDTO = new OneDayCalendarDTO();

            oneDayDTO.setYyyy((String) objects[0]);
            oneDayDTO.setMm((String) objects[1]);
            oneDayDTO.setDd((String) objects[2]);

            // 운동부위 정보를 DTO에 싣는다.
            String parts = (String) objects[3];

            if (parts != null && !parts.isEmpty()) {
                List<String> partsDTO = new ArrayList<>(Arrays.asList(parts.split(",")));
                oneDayDTO.setParts(partsDTO);
            }

            oneDayCalendarDTOList.add(oneDayDTO);
        }

        return OneMonthCalendarDTO
                .builder()
                        .oneDayCalendarDTOList(oneDayCalendarDTOList)
                .build();
    }

    /**
     * 02. 개인/그룹 일일 운동일지 리스트 조회.
     *  - IN = 회원번호 , YYYY/MM/DD, 개인그룹구분코드(01:개인/02:그룹)
     *  - OUT = DD일의 운동일지 리스트 조회. (운동일지 내 모든 운동부위를 포함)
     */
    @Override
    public OneDayJnalsDTO getOneDayJnals(Long Id, String yyyyMmDd, String mbrGrpClsfCd) {

        OneDayJnalsDTO oneDayJnalsDTO = OneDayJnalsDTO.builder().build();

        String yyyy = yyyyMmDd.substring(0, 4);
        String mm = yyyyMmDd.substring(4,6);
        String dd = yyyyMmDd.substring(6,8);

        List<WkoutJnal> jnalEntities = null;
        if (("01").equals(mbrGrpClsfCd)) {  // 개인 일지 리스트
            jnalEntities = wkoutJnalRepository.findWkoutJnalsByMbrIdAndYyyyMmDd(
                    Id, YyyyMmDd.builder().yyyy(yyyy).mm(mm).dd(dd).build()
            );
        } else if ("02".equals(mbrGrpClsfCd)) {  // 그룹 일지 리스트
            jnalEntities = wkoutJnalRepository.findWkoutJnalsByGrpIdAndYyyyMmDd(
                    Id, YyyyMmDd.builder().yyyy(yyyy).mm(mm).dd(dd).build()
            );
        } else {
            return null;
        }

        for (WkoutJnal jnalEntity : jnalEntities) {

            List<Object[]> methods = wkoutJnalMethodRepository.findAllByJnalId(jnalEntity.getJnalId());
            List<WkoutJnalMethodDTO> methodsDTO = jnalMethodsTojnalMethodDTOs(methods);

            WkoutJnalDTO jnalDTO = WkoutJnalDTO
                    .builder()
                    .jnalId(jnalEntity.getJnalId())
                    .mbrId(jnalEntity.getMember().getMbrId())
                    .mbrNm(jnalEntity.getMember().getMbrNm())
                    .profImgPath(jnalEntity.getMember().getProfImgPath())
                    .yyyy(jnalEntity.getYyyyMmDd().getYyyy())
                    .mm(jnalEntity.getYyyyMmDd().getMm())
                    .dd(jnalEntity.getYyyyMmDd().getDd())
                    .wkoutJnalMethodDTOList(methodsDTO)
                    .build();

            oneDayJnalsDTO.getWkoutJnalDTO().add(jnalDTO);
        }

        return oneDayJnalsDTO;

    }

    /**
     * 03. 개인 운동일지 조회.(PK)
     *  - IN = 운동일지ID
     *  - OUT = 운동일지 1건 조회. (운동일지 내 모든 운동부위를 포함)
     */
    @Override
    public WkoutJnalDTO getOneJnal(Long jnalId) {

        WkoutJnal jnalEntity = wkoutJnalRepository.findById(jnalId).get();
        List<Object[]> jnalMethods = wkoutJnalMethodRepository.findAllByJnalId(jnalId);

        return WkoutJnalDTO
                .builder()
                .jnalId(jnalEntity.getJnalId())
                .yyyy(jnalEntity.getYyyyMmDd().getYyyy())
                .mm(jnalEntity.getYyyyMmDd().getMm())
                .dd(jnalEntity.getYyyyMmDd().getDd())
                .comments(jnalEntity.getComments())
                .wkoutJnalMethodDTOList(jnalMethodsTojnalMethodDTOs(jnalMethods))
                .build();
    }

    /**
     * 04. 개인 운동일지 저장.
     *  - IN = 운동일지 DTO
     *  - OUT = 저장한 운동일지ID
     */
    @Override
    @Transactional
    public Long createJnal(WkoutJnalDTO wkoutJnalDTO, Long mbrId) {

        /* (1) 저널 저장 */
        WkoutJnal newJnal = wkoutJnalRepository.save(WkoutJnal
                .builder()
                .jnalId(wkoutJnalDTO.getJnalId()) // *jnalId=null 저장 / *jnalId!=null 편집
                .yyyyMmDd(YyyyMmDd
                        .builder()
                        .yyyy(wkoutJnalDTO.getYyyy())
                        .mm(wkoutJnalDTO.getMm())
                        .dd(wkoutJnalDTO.getDd())
                        .build())
                .member(Member
                        .builder()
                        .mbrId(mbrId)
                        .build())
                .comments(wkoutJnalDTO.getComments())
                .build());

        /* (2) 저널/운동종목 저장 */
        List<WkoutJnalMethodDTO> wkoutJnalMethodDTOList = wkoutJnalDTO.getWkoutJnalMethodDTOList();
        List<WkoutJnalMethod> wkoutJnalMethodList = new ArrayList<>();

        for (WkoutJnalMethodDTO wkoutJnalMethodDTO : wkoutJnalMethodDTOList) {
            wkoutJnalMethodList.add(
                    WkoutJnalMethod
                            .builder()
                            .wkoutJnal(WkoutJnal
                                    .builder()
                                    .jnalId(newJnal.getJnalId())
                                    .build())
                            .wkoutMethod(WkoutMethod
                                    .builder()
                                    .methodId(wkoutJnalMethodDTO.getMethodId())
                                    .build())
                            .weight(wkoutJnalMethodDTO.getWeight())
                            .sets(wkoutJnalMethodDTO.getSets())
                            .reps(wkoutJnalMethodDTO.getReps())
                            .reps(wkoutJnalMethodDTO.getRestTime())
                            .build()
            );
        }

        wkoutJnalMethodRepository.saveAll(wkoutJnalMethodList);

        return newJnal.getJnalId();
    }

    /**
     * 05. 개인 운동일지 삭제
     *  - IN = 저널ID
     *  - OUT = []
     * */
    @Override
    @Transactional
    public void removeJnal(Long jnalId) {
        // WkoutJnalMethod.WkoutJnal 필드의 @OnDelete(cascade) 옵션에 의해 일괄삭제처리.
        wkoutJnalRepository.deleteById(jnalId);
    }

    /**
     * 06. 개인 운동일지 편집
     *  - IN = 운동일지DTO
     *  - OUT = 편집된 운동일지ID
     * */
    @Override
    @Transactional
    public Long modifyJnal(WkoutJnalDTO wkoutJnalDTO) {
        // (1) 저널종목 삭제
        wkoutJnalMethodRepository.deleteByJnalId(wkoutJnalDTO.getJnalId());

        // (2) 저널종목 삽입
        List<WkoutJnalMethod> wkoutJnalMethodList = new ArrayList<>();
        for (WkoutJnalMethodDTO wkoutJnalMethodDTO : wkoutJnalDTO.getWkoutJnalMethodDTOList()) {
            wkoutJnalMethodList.add(
                    WkoutJnalMethod
                            .builder()
                            .jnalMethodId(wkoutJnalMethodDTO.getJnalMethodId())
                            .wkoutJnal(WkoutJnal
                                    .builder()
                                    .jnalId(wkoutJnalDTO.getJnalId())
                                    .build())
                            .wkoutMethod(WkoutMethod
                                    .builder()
                                    .methodId(wkoutJnalMethodDTO.getMethodId())
                                    .build())
                            .weight(wkoutJnalMethodDTO.getWeight())
                            .sets(wkoutJnalMethodDTO.getSets())
                            .reps(wkoutJnalMethodDTO.getReps())
                            .restTime(wkoutJnalMethodDTO.getRestTime())
                            .build()
            );
        }
        wkoutJnalMethodRepository.saveAll(wkoutJnalMethodList);

        // (3) 저널 수정  (dirty-checking)
        WkoutJnal wkoutJnal = wkoutJnalRepository.findById(wkoutJnalDTO.getJnalId()).get();
        wkoutJnal.setYyyyMmDd(YyyyMmDd
                .builder()
                .yyyy(wkoutJnalDTO.getYyyy())
                .mm(wkoutJnalDTO.getMm())
                .dd(wkoutJnalDTO.getDd())
                .build());
        wkoutJnal.setComments(wkoutJnalDTO.getComments());

        return wkoutJnalDTO.getJnalId();
    }

}
