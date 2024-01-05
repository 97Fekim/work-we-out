package com.fekim.workweout.online.member.repository;

import com.fekim.workweout.online.member.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
