package com.fekim.workweout.online.jnal.service.dto;

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
public class WkoutJnalDTO {

    private Long jnalId;
    private String yyyy;
    private String mm;
    private String dd;
    private String comments;
    
    // 운동종목 및 세트수/랩수/휴식시간이 담긴 정보 리스트
    @Builder.Default
    private List<WkoutJnalMethodDTO> wkoutJnalMethodDTOList = new ArrayList<>();

}
