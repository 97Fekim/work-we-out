package com.fekim.workweout.online.date.repository;

import com.fekim.workweout.online.date.repository.entity.Date;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<Date, YyyyMmDd> {
}
