package com.fekim.workweout.online.stat.repository.entity.key;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class YyyyMmMbr implements Serializable {

    @Embedded
    private YyyyMm yyyyMm;

    @Column(name="MBR_ID", nullable = false, columnDefinition = "NUMBER(12)")
    private String mbrId;
}
