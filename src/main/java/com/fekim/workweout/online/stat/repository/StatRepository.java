package com.fekim.workweout.online.stat.repository;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.jnal.repository.entity.WkoutJnalMethod;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatRepository extends Repository<WkoutJnalMethod, Long> {

    @Query(value = "" +
            "select WM.targetPart AS targetPart," +
            "       SUM(WJM.sets) AS totalSets " +
            "from WkoutJnal WJ " +
            "join Date D on WJ.yyyyMmDd = D.yyyyMmDd " +
            "join WkoutJnalMethod WJM on WJ.jnalId = WJM.wkoutJnal.jnalId " +
            "join WkoutMethod WM on WJM.wkoutMethod.methodId = WM.methodId " +
            "where WJ.member.mbrId = :#{#mbrId} " +
            "  and D.yyyyMmW.cuofYyyy = :#{#yyyyMmW.cuofYyyy} " +
            "  and D.yyyyMmW.cuofMm = :#{#yyyyMmW.cuofMm} " +
            "  and D.yyyyMmW.cuofWeek = :#{#yyyyMmW.cuofWeek} " +
            "group by WM.methodNm " +
            "order by WM.methodId ")
    List<Object[]> findWeeklyMethodTotalSets(@Param("mbrId") Long mbrId, @Param("yyyyMmW") YyyyMmW yyyyMmW);

    @Query(value = "" +
            "select WM.targetPart AS targetPart," +
            "       SUM(WJM.sets) AS totalSets " +
            "from WkoutJnal WJ " +
            "join Date D on WJ.yyyyMmDd = D.yyyyMmDd " +
            "join WkoutJnalMethod WJM on WJ.jnalId = WJM.wkoutJnal.jnalId " +
            "join WkoutMethod WM on WJM.wkoutMethod.methodId = WM.methodId " +
            "where WJ.member.mbrId = :#{#mbrId} " +
            "  and D.yyyyMmW.cuofYyyy = :#{#yyyyMm.cuofYyyy} " +
            "  and D.yyyyMmW.cuofMm = :#{#yyyyMm.cuofMm} " +
            "group by WM.methodNm " +
            "order by WM.methodId ")
    List<Object[]> findMonthlyMethodTotalSets(@Param("mbrId") Long mbrId, @Param("yyyyMm") YyyyMm yyyyMm);

}
