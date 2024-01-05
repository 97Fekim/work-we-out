package com.fekim.workweout.online.stat.repository;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.stat.repository.entity.MonthlyWkoutStatRslt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyWkoutStatRsltRepository extends JpaRepository<MonthlyWkoutStatRslt, YyyyMmW> {
}
