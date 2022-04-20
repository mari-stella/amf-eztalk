package com.hea.eztalk.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{

    Optional<Member> findByMemberId(String memberId) ;
}
