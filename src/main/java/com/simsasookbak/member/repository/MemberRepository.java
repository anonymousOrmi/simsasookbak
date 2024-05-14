package com.simsasookbak.member.repository;

import com.simsasookbak.member.domain.Member;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByEmail(String email);

    Page<Member> findAll(Pageable pageable);

    @Query("SELECT m FROM Member m WHERE m.name = :name")
    Optional<List<Member>> getSearchMemberByName(@Param("name") String name);



}
