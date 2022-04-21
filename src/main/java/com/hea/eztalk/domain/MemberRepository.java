package com.hea.eztalk.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>{

    Optional<Member> findByAddress(Address address);

    Optional<Member> findByEmail(String email);

}
