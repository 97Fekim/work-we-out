package com.fekim.workweout.online.date.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class YyyyMmW {

    @Column(name="CUOF_YEAR", nullable = false, columnDefinition = "VARCHAR2(4)")
    private String cuofYear;

    @Column(name="CUOF_MONTH", nullable = false, columnDefinition = "VARCHAR2(2)")
    private String cuofMonth;

    @Column(name="CUOF_WEEK", nullable = false, columnDefinition = "VARCHAR2(1)")
    private String cuofWeek;

}
