package com.janek.simplesns.domain.member.repository;

import com.janek.simplesns.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long id);

    List<Member> findAllByIdIn(List<Long> ids);

}
