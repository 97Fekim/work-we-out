package com.fekim.workweout.online.stat.repository.entity.key;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YyyyMmWMbr extends YyyyMmW implements Serializable {

    @Column(name="MBR_ID", nullable = false, columnDefinition = "NUMBER(12)")
    private String mbrId;
}
