package com.fekim.workweout.online.jnal.repository;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.jnal.repository.entity.WkoutJnal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WkoutJnalRepository extends JpaRepository<WkoutJnal, Long> {

    @Query (value =
            "SELECT " +
            "    TD.YYYY     AS yyyy, " +
            "    TD.MM       AS mm, " +
            "    TD.DD       AS dd, " +
            "    JNALS.PARTS AS parts " +
            "FROM TBL_DATE TD " +
            "LEFT OUTER JOIN " +
            "    (SELECT WJ.YYYY AS yyyy, " +
            "            WJ.MM   AS mm, " +
            "            WJ.DD   AS dd, " +
            "            LISTAGG(DISTINCT WM.TARGET_PART, ',') WITHIN GROUP(ORDER BY WJ.YYYY, WJ.MM, WJ.DD) AS PARTS " +
            "     FROM WKOUT_JNAL WJ " +
            "     JOIN MEMBER M " +
            "     ON WJ.MBR_ID = M.MBR_ID " +
            "     JOIN WKOUT_JNAL_METHOD WJM " +
            "     ON WJM.JNAL_ID = WJ.JNAL_ID " +
            "     JOIN WKOUT_METHOD WM " +
            "     ON WM.METHOD_ID = WJM.METHOD_ID " +
            "     WHERE M.MBR_ID = :#{#mbrId} " +
            "     GROUP BY WJ.YYYY, WJ.MM, WJ.DD) JNALS " +
            "ON  TD.YYYY = JNALS.YYYY " +
            "AND TD.MM = JNALS.MM " +
            "AND TD.DD = JNALS.DD " +
            "WHERE TD.YYYY = :#{#yyyyMm.cuofYyyy} " +
            "  AND TD.MM = :#{#yyyyMm.cuofMm} " +
            "ORDER BY yyyy, mm, dd; "
            , nativeQuery = true)
    List<Object[]> findOneMonthCalendarObjects(@Param("mbrId")Long mbrId, @Param("yyyyMm")YyyyMm yyyyMm);



}
