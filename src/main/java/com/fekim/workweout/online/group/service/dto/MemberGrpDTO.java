package com.fekim.workweout.online.group.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberGrpDTO {

    private Long mbrGrpId;
    private Long mbrId;
    private String mbrNm;
    private String profImgPath;

}
