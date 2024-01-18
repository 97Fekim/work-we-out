package com.fekim.workweout.online.member.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRegisterDTO {

    private String mbrNm;
    private String password;
    private String phone;
    private String email;
    private String statSmsSendYn;

}
