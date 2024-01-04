package com.fekim.workweout.online.date.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Data
@Embeddable
public class YyyyMmDd implements Serializable {

    @Column(name="YEAR", nullable = false, columnDefinition = "VARCHAR2(4)")
    private String year;

    @Column(name="MONTH", nullable = false, columnDefinition = "VARCHAR2(2)")
    private String month;

    @Column(name="DAY", nullable = false, columnDefinition = "VARCHAR2(2)")
    private String day;

}
