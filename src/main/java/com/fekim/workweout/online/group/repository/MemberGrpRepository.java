package com.fekim.workweout.online.group.repository;

import com.fekim.workweout.online.group.repository.entity.Grp;
import com.fekim.workweout.online.group.repository.entity.MemberGrp;
import com.fekim.workweout.online.member.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberGrpRepository extends JpaRepository<MemberGrp, Long> {

    @Query ("SELECT G " +
            "FROM MemberGrp MG " +
            "JOIN MG.grp G " +
            "WHERE MG.member.mbrId = :mbrId")
    List<Grp> findGrpListByMbrId(@Param("mbrId")Long mbrId);

}
