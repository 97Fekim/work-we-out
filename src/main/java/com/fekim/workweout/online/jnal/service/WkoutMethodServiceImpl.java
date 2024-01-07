package com.fekim.workweout.online.jnal.service;

import com.fekim.workweout.online.jnal.repository.WkoutMethodRepository;
import com.fekim.workweout.online.jnal.repository.entity.WkoutMethod;
import com.fekim.workweout.online.jnal.service.dto.WkoutMethodDTO;
import com.fekim.workweout.online.jnal.service.dto.WkoutMethodListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class WkoutMethodServiceImpl implements WkoutMethodService{

    private final WkoutMethodRepository wkoutMethodRepository;

    /**
     * 01. 전체 운동종목 조회
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

}
