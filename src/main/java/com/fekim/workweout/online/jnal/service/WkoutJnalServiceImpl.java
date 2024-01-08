package com.fekim.workweout.online.jnal.service;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.jnal.repository.WkoutJnalMethodRepository;
import com.fekim.workweout.online.jnal.repository.WkoutJnalRepository;
import com.fekim.workweout.online.jnal.repository.WkoutMethodRepository;
import com.fekim.workweout.online.jnal.repository.entity.WkoutJnal;
import com.fekim.workweout.online.jnal.repository.entity.WkoutJnalMethod;
import com.fekim.workweout.online.jnal.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class WkoutJnalServiceImpl implements WkoutJnalService {

    private final WkoutJnalRepository wkoutJnalRepository;
    private final WkoutJnalMethodRepository wkoutJnalMethodRepository;

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
     * 02. 개인 운동일지 조회.
     *  - IN = 회원번호 , YYYY/MM/DD
     *  - OUT = DD일의 운동일지 조회. (운동일지 내 모든 운동부위를 포함)
     */
    @Override
    public OneDayJnalsDTO getOneDayJnals(Long mbrId, String yyyyMmDd) {

        OneDayJnalsDTO oneDayJnalsDTO = OneDayJnalsDTO.builder().build();

        String yyyy = yyyyMmDd.substring(0, 4);
        String mm = yyyyMmDd.substring(4,6);
        String dd = yyyyMmDd.substring(6,8);

        List<WkoutJnal> jnalEntities = wkoutJnalRepository.findWkoutJnalsByMbrIdAndYyyyMmDd(
                mbrId, YyyyMmDd.builder().yyyy(yyyy).mm(mm).dd(dd).build()
        );

        for (WkoutJnal jnalEntity : jnalEntities) {

            List<Object[]> methods = wkoutJnalMethodRepository.findWkoutJnalMethodsByJnalId(jnalEntity.getJnalId());
            List<WkoutJnalMethodDTO> methodsDTO = new ArrayList<>();

            for (Object[] method : methods) {
                methodsDTO.add(
                        jnalMethodTojnalMethodDTO(method)
                );
            }

            WkoutJnalDTO jnalDTO = WkoutJnalDTO
                    .builder()
                    .jnalId(jnalEntity.getJnalId())
                    .yyyy(jnalEntity.getYyyyMmDd().getYyyy())
                    .mm(jnalEntity.getYyyyMmDd().getMm())
                    .dd(jnalEntity.getYyyyMmDd().getDd())
                    .wkoutJnalMethodDTOList(methodsDTO)
                    .build();

            oneDayJnalsDTO.getWkoutJnalDTO().add(jnalDTO);
        }

        return oneDayJnalsDTO;

    }

}
