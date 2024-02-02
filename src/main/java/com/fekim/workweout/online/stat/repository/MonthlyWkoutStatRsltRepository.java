package com.fekim.workweout.online.stat.repository;

import com.fekim.workweout.online.date.repository.entity.key.YyyyMm;
import com.fekim.workweout.online.date.repository.entity.key.YyyyMmW;
import com.fekim.workweout.online.stat.repository.entity.MonthlyWkoutStatRslt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MonthlyWkoutStatRsltRepository extends JpaRepository<MonthlyWkoutStatRslt, YyyyMm> {

    @Query(value = "" +
            "select count(MR) " +
            "from MonthlyWkoutStatRslt MR " +
            "where MR.yyyyMmMbr.yyyyMm.cuofYyyy = :#{#yyyyMm.cuofYyyy} " +
            "  and MR.yyyyMmMbr.yyyyMm.cuofMm = :#{#yyyyMm.cuofMm} " +
            "  and MR.smsSendRsltClsfCd = :#{#smsSendRsltClsfCd} ")
    Long findMonthlyTotalCntBySmsSendRsltClsfCd(@Param("yyyyMm") YyyyMm yyyyMm,
                                               @Param("smsSendRsltClsfCd") String smsSendRsltClsfCd);

}
