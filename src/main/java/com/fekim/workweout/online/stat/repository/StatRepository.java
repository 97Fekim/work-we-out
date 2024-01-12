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


    @Query(value = "" +
            "SELECT " +
            "  BEFORE_WEEK.METHOD_ID AS methodId,  " +
            "  BEFORE_WEEK.METHOD_NM AS methodNm,  " +
            "  BEFORE_WEEK.MAX_WEIGHT AS bfWeekMaxWeight,  " +
            "  CUR_WEEK.MAX_WEIGHT AS curWeekMaxWeight,  " +
            "  CUR_WEEK.MAX_WEIGHT - BEFORE_WEEK.MAX_WEIGHT AS weightIncrease  " +
            "FROM " +
            "   (SELECT WM.METHOD_ID, WM.METHOD_NM, MAX(WJM.WEIGHT) AS MAX_WEIGHT  " +
            "   FROM " +
            "   WKOUT_JNAL WJ, " +
            "   WKOUT_JNAL_METHOD WJM, " +
            "   WKOUT_METHOD WM, " +
            "   TBL_DATE D " +
            "   WHERE 1=1  " +
            "     AND WJ.JNAL_ID = WJM.JNAL_ID " +
            "     AND WJM.METHOD_ID = WM.METHOD_ID " +
            "     AND WJ.YYYY = D.YYYY " +
            "     AND WJ.MM = D.MM " +
            "     AND WJ.DD = D.DD " +
            "     AND WJ.MBR_ID = :#{#mbrId} " +
            "     AND D.CUOF_YYYY = :#{#bfYyyyMmW.cuofYyyy} " +
            "     AND D.CUOF_MM = :#{#bfYyyyMmW.cuofMm} " +
            "     AND D.CUOF_WEEK = :#{#bfYyyyMmW.cuofWeek} " +
            "   GROUP BY WM.METHOD_ID) BEFORE_WEEK, " +
            "   (SELECT WM.METHOD_ID, WM.METHOD_NM, MAX(WJM.WEIGHT)  AS MAX_WEIGHT  " +
            "   FROM " +
            "   WKOUT_JNAL WJ, " +
            "   WKOUT_JNAL_METHOD WJM, " +
            "   WKOUT_METHOD WM, " +
            "   TBL_DATE D " +
            "   WHERE 1=1  " +
            "     AND WJ.JNAL_ID = WJM.JNAL_ID " +
            "     AND WJM.METHOD_ID = WM.METHOD_ID " +
            "     AND WJ.YYYY = D.YYYY " +
            "     AND WJ.MM = D.MM " +
            "     AND WJ.DD = D.DD " +
            "     AND WJ.MBR_ID = :#{#mbrId} " +
            "     AND D.CUOF_YYYY = :#{#curYyyyMmW.cuofYyyy} " +
            "     AND D.CUOF_MM = :#{#curYyyyMmW.cuofMm} " +
            "     AND D.CUOF_WEEK = :#{#curYyyyMmW.cuofWeek} " +
            "GROUP BY WM.METHOD_ID) CUR_WEEK " +
            "WHERE BEFORE_WEEK.METHOD_ID = CUR_WEEK.METHOD_ID " +
            "ORDER BY methodId;"
            , nativeQuery = true)
    List<Object[]> findWeeklyMethodWeiIncs(@Param("mbrId") Long mbrId,
                                           @Param("bfYyyyMmW") YyyyMmW bfYyyyMmW,
                                           @Param("curYyyyMmW") YyyyMmW curYyyyMmW);

    @Query(value = "" +
            "SELECT " +
            "  BEFORE_MONTH.METHOD_ID AS methodId,  " +
            "  BEFORE_MONTH.METHOD_NM AS methodNm,  " +
            "  BEFORE_MONTH.MAX_WEIGHT AS bfMonthMaxWeight,  " +
            "  CUR_MONTH.MAX_WEIGHT AS curMonthMaxWeight,  " +
            "  CUR_MONTH.MAX_WEIGHT - BEFORE_MONTH.MAX_WEIGHT AS weightIncrease  " +
            "FROM " +
            "   (SELECT WM.METHOD_ID, WM.METHOD_NM, MAX(WJM.WEIGHT) AS MAX_WEIGHT  " +
            "   FROM " +
            "   WKOUT_JNAL WJ, " +
            "   WKOUT_JNAL_METHOD WJM, " +
            "   WKOUT_METHOD WM, " +
            "   TBL_DATE D " +
            "   WHERE 1=1  " +
            "     AND WJ.JNAL_ID = WJM.JNAL_ID " +
            "     AND WJM.METHOD_ID = WM.METHOD_ID " +
            "     AND WJ.YYYY = D.YYYY " +
            "     AND WJ.MM = D.MM " +
            "     AND WJ.DD = D.DD " +
            "     AND WJ.MBR_ID = :#{#mbrId} " +
            "     AND D.CUOF_YYYY = :#{#bfYyyyMm.cuofYyyy} " +
            "     AND D.CUOF_MM = :#{#bfYyyyMm.cuofMm} " +
            "   GROUP BY WM.METHOD_ID) BEFORE_MONTH, " +
            "   (SELECT WM.METHOD_ID, WM.METHOD_NM, MAX(WJM.WEIGHT)  AS MAX_WEIGHT  " +
            "   FROM " +
            "   WKOUT_JNAL WJ, " +
            "   WKOUT_JNAL_METHOD WJM, " +
            "   WKOUT_METHOD WM, " +
            "   TBL_DATE D " +
            "   WHERE 1=1  " +
            "     AND WJ.JNAL_ID = WJM.JNAL_ID " +
            "     AND WJM.METHOD_ID = WM.METHOD_ID " +
            "     AND WJ.YYYY = D.YYYY " +
            "     AND WJ.MM = D.MM " +
            "     AND WJ.DD = D.DD " +
            "     AND WJ.MBR_ID = :#{#mbrId} " +
            "     AND D.CUOF_YYYY = :#{#curYyyyMm.cuofYyyy} " +
            "     AND D.CUOF_MM = :#{#curYyyyMm.cuofMm} " +
            "GROUP BY WM.METHOD_ID) CUR_MONTH " +
            "WHERE BEFORE_MONTH.METHOD_ID = CUR_MONTH.METHOD_ID " +
            "ORDER BY methodId;"
            , nativeQuery = true)
    List<Object[]> findMonthlyMethodWeiIncs(@Param("mbrId") Long mbrId,
                                           @Param("bfYyyyMm") YyyyMm bfYyyyMm,
                                           @Param("curYyyyMm") YyyyMm curYyyyMm);

}
