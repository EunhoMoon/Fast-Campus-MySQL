package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;

public interface MemberRepository {

    public Member save(Member member);

}
