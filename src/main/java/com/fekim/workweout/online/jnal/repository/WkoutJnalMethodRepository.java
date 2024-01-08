package com.fekim.workweout.online.jnal.repository;

import com.fekim.workweout.online.jnal.repository.entity.WkoutJnalMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WkoutJnalMethodRepository extends JpaRepository<WkoutJnalMethod, Long> {

    @Query(value ="" +
            "select WM.methodNm AS methodNm," +
            "WM.targetPart AS targetPart," +
            "WJM.weight AS weight, " +
            "WJM.sets AS sets," +
            "WJM.reps AS reps," +
            "WJM.restTime AS restTime " +
            "from WkoutJnalMethod WJM " +
            "join WJM.wkoutMethod WM " +
            "where WJM.wkoutJnal.jnalId = :#{#jnalId}")
    List<Object[]> findWkoutJnalMethodsByJnalId(@Param("jnalId") Long jnalId);

}
