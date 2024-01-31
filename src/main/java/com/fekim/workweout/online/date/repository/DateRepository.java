package com.fekim.workweout.online.date.repository;

import com.fekim.workweout.online.date.repository.entity.Date;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmDd;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DateRepository extends JpaRepository<Date, YyyyMmDd> {

    @Query(value = "" +
            "SELECT  " +
            "  BF_CUOF_YYYY AS bfCuofYyyy, " +
            "  BF_CUOF_MM AS bfCuofMm, " +
            "  BF_CUOF_WEEK AS bfCuofWeek " +
            "FROM  " +
            "  (SELECT  " +
            "     CUOF_YYYY,  " +
            "     CUOF_MM,  " +
            "     CUOF_WEEK, " +
            "     LAG(CUOF_YYYY, :#{#range} ) OVER (ORDER BY CUOF_YYYY, CUOF_MM, CUOF_WEEK) AS BF_CUOF_YYYY, " +
            "     LAG(CUOF_MM, :#{#range} ) OVER (ORDER BY CUOF_YYYY, CUOF_MM, CUOF_WEEK) AS BF_CUOF_MM, " +
            "     LAG(CUOF_WEEK, :#{#range} ) OVER (ORDER BY CUOF_YYYY, CUOF_MM, CUOF_WEEK) AS BF_CUOF_WEEK " +
            "   FROM TBL_DATE " +
            "   GROUP BY CUOF_YYYY, CUOF_MM, CUOF_WEEK " +
            "   ORDER BY CUOF_YYYY, CUOF_MM, CUOF_WEEK ) CUOF_DATE " +
            "WHERE 1=1  " +
            "  AND CUOF_YYYY = :#{#cuofYyyyMmW.cuofYyyy} " +
            "  AND CUOF_MM = :#{#cuofYyyyMmW.cuofMm} " +
            "  AND CUOF_WEEK = :#{#cuofYyyyMmW.cuofWeek} " +
            "ORDER BY BF_CUOF_YYYY, BF_CUOF_MM, BF_CUOF_WEEK; "
            , nativeQuery = true)
    List<Object[]> findBeforeCuofYyyyMmW(@Param("cuofYyyyMmW") YyyyMmW cuofYyyyMmW,
                                   @Param("range") Long range);

    @Query(value = "" +
            "SELECT  " +
            "  AF_CUOF_YYYY AS afCuofYyyy, " +
            "  AF_CUOF_MM AS afCuofMm, " +
            "  AF_CUOF_WEEK AS afCuofWeek " +
            "FROM  " +
            "  (SELECT  " +
            "     CUOF_YYYY,  " +
            "     CUOF_MM,  " +
            "     CUOF_WEEK, " +
            "     LEAD(CUOF_YYYY, :#{#range} ) OVER (ORDER BY CUOF_YYYY, CUOF_MM, CUOF_WEEK) AS AF_CUOF_YYYY, " +
            "     LEAD(CUOF_MM, :#{#range} ) OVER (ORDER BY CUOF_YYYY, CUOF_MM, CUOF_WEEK) AS AF_CUOF_MM, " +
            "     LEAD(CUOF_WEEK, :#{#range} ) OVER (ORDER BY CUOF_YYYY, CUOF_MM, CUOF_WEEK) AS AF_CUOF_WEEK " +
            "   FROM TBL_DATE " +
            "   GROUP BY CUOF_YYYY, CUOF_MM, CUOF_WEEK " +
            "   ORDER BY CUOF_YYYY, CUOF_MM, CUOF_WEEK ) CUOF_DATE " +
            "WHERE 1=1  " +
            "  AND CUOF_YYYY = :#{#cuofYyyyMmW.cuofYyyy} " +
            "  AND CUOF_MM = :#{#cuofYyyyMmW.cuofMm} " +
            "  AND CUOF_WEEK = :#{#cuofYyyyMmW.cuofWeek} " +
            "ORDER BY AF_CUOF_YYYY, AF_CUOF_MM, AF_CUOF_WEEK; "
            , nativeQuery = true)
    List<Object[]> findAfterCuofYyyyMmW(@Param("cuofYyyyMmW") YyyyMmW cuofYyyyMmW,
                                         @Param("range") Long range);

    @Query(value = "" +
            "SELECT  " +
            "  BF_CUOF_YYYY AS bfCuofYyyy, " +
            "  BF_CUOF_MM AS bfCuofMm " +
            "FROM  " +
            "  (SELECT  " +
            "     CUOF_YYYY,  " +
            "     CUOF_MM,  " +
            "     LAG(CUOF_YYYY, :#{#range} ) OVER (ORDER BY CUOF_YYYY, CUOF_MM) AS BF_CUOF_YYYY, " +
            "     LAG(CUOF_MM, :#{#range} ) OVER (ORDER BY CUOF_YYYY, CUOF_MM) AS BF_CUOF_MM " +
            "   FROM TBL_DATE " +
            "   GROUP BY CUOF_YYYY, CUOF_MM " +
            "   ORDER BY CUOF_YYYY, CUOF_MM ) CUOF_DATE " +
            "WHERE 1=1  " +
            "  AND CUOF_YYYY = :#{#cuofYyyyMm.cuofYyyy} " +
            "  AND CUOF_MM = :#{#cuofYyyyMm.cuofMm} " +
            "ORDER BY BF_CUOF_YYYY, BF_CUOF_MM; "
            , nativeQuery = true)
    List<Object[]> findBeforeCuofYyyyMm(@Param("cuofYyyyMm") YyyyMm cuofYyyyMm,
                                         @Param("range") Long range);

    @Query(value = "" +
            "SELECT  " +
            "  AF_CUOF_YYYY AS afCuofYyyy, " +
            "  AF_CUOF_MM AS afCuofMm " +
            "FROM  " +
            "  (SELECT  " +
            "     CUOF_YYYY,  " +
            "     CUOF_MM,  " +
            "     LEAD(CUOF_YYYY, :#{#range} ) OVER (ORDER BY CUOF_YYYY, CUOF_MM) AS AF_CUOF_YYYY, " +
            "     LEAD(CUOF_MM, :#{#range} ) OVER (ORDER BY CUOF_YYYY, CUOF_MM) AS AF_CUOF_MM " +
            "   FROM TBL_DATE " +
            "   GROUP BY CUOF_YYYY, CUOF_MM " +
            "   ORDER BY CUOF_YYYY, CUOF_MM ) CUOF_DATE " +
            "WHERE 1=1  " +
            "  AND CUOF_YYYY = :#{#cuofYyyyMm.cuofYyyy} " +
            "  AND CUOF_MM = :#{#cuofYyyyMm.cuofMm} " +
            "ORDER BY AF_CUOF_YYYY, AF_CUOF_MM; "
            , nativeQuery = true)
    List<Object[]> findAfterCuofYyyyMm(@Param("cuofYyyyMm") YyyyMm cuofYyyyMm,
                                        @Param("range") Long range);

    @Query(value = "" +
            "SELECT  " +
            "  BF_YYYY AS bfYyyy, " +
            "  BF_MM AS bfMm ," +
            "  BF_DD AS bfDd , " +
            "  BF_DAY_OF_WEEK_CLSF_CD AS bfDayOfWeekClsfCd " +
            "FROM  " +
            "  (SELECT  " +
            "     YYYY,  " +
            "     MM,  " +
            "     DD,  " +
            "     LAG(YYYY, :#{#range} ) OVER (ORDER BY YYYY, MM, DD) AS BF_YYYY, " +
            "     LAG(MM, :#{#range} ) OVER (ORDER BY YYYY, MM, DD) AS BF_MM, " +
            "     LAG(DD, :#{#range} ) OVER (ORDER BY YYYY, MM, DD) AS BF_DD, " +
            "     LAG(DAY_OF_WEEK_CLSF_CD, :#{#range} ) OVER (ORDER BY YYYY, MM, DD) AS BF_DAY_OF_WEEK_CLSF_CD " +
            "   FROM TBL_DATE " +
            "   ORDER BY YYYY, MM, DD ) DATE " +
            "WHERE 1=1  " +
            "  AND YYYY = :#{#yyyyMmDd.yyyy} " +
            "  AND MM = :#{#yyyyMmDd.mm} " +
            "  AND DD = :#{#yyyyMmDd.dd} " +
            "ORDER BY BF_YYYY, BF_MM, BF_DD; "
            , nativeQuery = true)
    List<Object[]> findBeforeYyyyMmDd(@Param("yyyyMmDd") YyyyMmDd yyyyMmDd,
                                          @Param("range") Long range);

    @Query(value = "" +
            "SELECT  " +
            "  AF_YYYY AS afYyyy, " +
            "  AF_MM AS afMm ," +
            "  AF_DD AS afDd , " +
            "  AF_DAY_OF_WEEK_CLSF_CD AS afDayOfWeekClsfCd " +
            "FROM  " +
            "  (SELECT  " +
            "     YYYY,  " +
            "     MM,  " +
            "     DD,  " +
            "     LEAD(YYYY, :#{#range} ) OVER (ORDER BY YYYY, MM, DD) AS AF_YYYY, " +
            "     LEAD(MM, :#{#range} ) OVER (ORDER BY YYYY, MM, DD) AS AF_MM, " +
            "     LEAD(DD, :#{#range} ) OVER (ORDER BY YYYY, MM, DD) AS AF_DD, " +
            "     LEAD(DAY_OF_WEEK_CLSF_CD, :#{#range} ) OVER (ORDER BY YYYY, MM, DD) AS AF_DAY_OF_WEEK_CLSF_CD " +
            "   FROM TBL_DATE " +
            "   ORDER BY YYYY, MM, DD ) DATE " +
            "WHERE 1=1  " +
            "  AND YYYY = :#{#yyyyMmDd.yyyy} " +
            "  AND MM = :#{#yyyyMmDd.mm} " +
            "  AND DD = :#{#yyyyMmDd.dd} " +
            "ORDER BY AF_YYYY, AF_MM, AF_DD; "
            , nativeQuery = true)
    List<Object[]> findAfterYyyyMmDd(@Param("yyyyMmDd") YyyyMmDd yyyyMmDd,
                                      @Param("range") Long range);

}
