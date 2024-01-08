package com.fekim.workweout.online.member.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Long mbrId;
    private String mbrNm;
    private String phone;
    private String email;
    private String statSmsSendYn;
    private String mbrRoleClsfCd;
    private String profImgPath;

}
