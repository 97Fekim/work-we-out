package com.fekim.workweout.online.date.repository.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class YyyyMm implements Serializable {

    @Column(name="CUOF_YEAR", nullable = false, columnDefinition = "VARCHAR2(4)")
    private String cuofYear;

    @Column(name="CUOF_MONTH", nullable = false, columnDefinition = "VARCHAR2(2)")
    private String cuofMonth;
}
