package com.fekim.workweout.online.member.repository;

import com.fekim.workweout.online.group.repository.entity.Grp;
import com.fekim.workweout.online.group.repository.entity.MemberGrp;
import com.fekim.workweout.online.jnal.repository.entity.WkoutJnal;
import com.fekim.workweout.online.member.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "" +
            "select M " +
            "from Member M " +
            "where M.mbrId = :#{#mbrId}" +
            "  and M.mbrStatClsfCd = :#{#mbrStatClsfCd}")
    Member findMemberByIdAndMbrStatClsfCd(@Param("mbrId") Long mbrId,
                                          @Param("mbrStatClsfCd") String mbrStatClsfCd);

    Optional<Member> findByEmail(@Param("email") String email);

    @Query(value = "" +
            "select WJ " +
            "from WkoutJnal WJ " +
            "where WJ.jnalId = :#{#jnalId} " +
            "  and WJ.member.mbrId = :#{#mbrId}")
    Optional<WkoutJnal> findJnalByMbrIdAndJnalId(@Param("mbrId") Long mbrId,
                                                 @Param("jnalId") Long jnalId);

    @Query(value = "" +
            "select MG " +
            "from MemberGrp MG " +
            "where MG.member.mbrId = :#{#mbrId} " +
            "  and MG.grp.grpId = :#{#grpId}")
    Optional<MemberGrp> findMemberGrpByMbrIdAndGrpId(@Param("mbrId") Long mbrId,
                                                     @Param("grpId") Long grpId);

}
