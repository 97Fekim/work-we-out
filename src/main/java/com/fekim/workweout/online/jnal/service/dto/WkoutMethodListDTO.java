package com.fekim.workweout.online.jnal.service.dto;

import com.fekim.workweout.online.group.service.dto.GrpDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WkoutMethodListDTO {

    @Builder.Default  // Builder 패턴으로 생성시, List 인스턴스 생성함.
    private List<WkoutMethodDTO> wkoutMethodDTOList = new ArrayList<>();

}
