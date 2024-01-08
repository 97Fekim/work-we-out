package com.fekim.workweout.online.member.repository;

import com.fekim.workweout.online.member.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "" +
            "select M " +
            "from Member M " +
            "where M.mbrId = :#{#mbrId}" +
            "  and M.mbrStatClsfCd = :#{#mbrStatClsfCd}")
    Member findMemberByIdAndMbrStatClsfCd(@Param("mbrId") Long mbrId,
                                          @Param("mbrStatClsfCd") String mbrStatClsfCd);

}
