package com.fekim.workweout.online.jnal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JnalDTO {

    private Long jnalId;
    private String yyyy;
    private String mm;
    private String dd;
    private String comments;


}
