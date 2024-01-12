package com.fekim.workweout.online.stat.service.dto;


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
public class MethodWeiIncsDTO {

    @Builder.Default
    List<MethodWeiIncDTO> methodWeiIncDTOList = new ArrayList<>();

}
