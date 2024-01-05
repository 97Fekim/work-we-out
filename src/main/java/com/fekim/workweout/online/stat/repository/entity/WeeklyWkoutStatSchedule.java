package com.fekim.workweout.online.stat.repository.entity;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "WeeklyWkoutStatSchedule")
@Table(name="WEEKLY_WKOUT_STAT_SCHEDULE")
public class WeeklyWkoutStatSchedule {

    @EmbeddedId
    private YyyyMmW yyyyMmW;

    @Column(name="STAT_CPLN_YN", nullable = false, columnDefinition = "VARCHAR2(1)")
    private String statCplnYn;

}
