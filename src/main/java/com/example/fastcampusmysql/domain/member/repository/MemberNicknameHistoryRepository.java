package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;

import java.util.List;
import java.util.Optional;

public interface MemberNicknameHistoryRepository {

    List<MemberNicknameHistory> findAllByMemberId(Long memberId);

    MemberNicknameHistory save(MemberNicknameHistory member);

}
