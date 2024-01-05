package com.fekim.workweout.online.date.repository.entity.key;

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
public class YyyyMmW extends YyyyMm implements Serializable {

    @Column(name="CUOF_WEEK", nullable = false, columnDefinition = "VARCHAR2(1)")
    private String cuofWeek;

}
