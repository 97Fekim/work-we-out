package com.fekim.workweout.online.stat.repository;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.stat.repository.entity.MonthlyWkoutStatSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyWkoutStatScheduleRepository extends JpaRepository<MonthlyWkoutStatSchedule, YyyyMm> {
}
