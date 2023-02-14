package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository {

    public Member save(Member member);

    public Optional<Member> findById(Long id);

}
