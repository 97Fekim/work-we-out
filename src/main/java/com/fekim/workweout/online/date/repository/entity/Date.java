package com.fekim.workweout.online.date.repository.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name = "Date")
@Table(name="TBL_DATE")
public class Date {

    @EmbeddedId
    private YyyyMmDd yyyyMmDd;

    @Embedded
    private YyyyMmW yyyyMmW;

    @Column(name="DAY_OF_WEEK_CLSF_CD", nullable = false, columnDefinition = "VARCHAR(3)")
    private String dayOfWeekClsfCd;

    @Column(name="HOLY_DAY_YN", nullable = false, columnDefinition = "VARCHAR(1)")
    private String holyDayYn;

}
