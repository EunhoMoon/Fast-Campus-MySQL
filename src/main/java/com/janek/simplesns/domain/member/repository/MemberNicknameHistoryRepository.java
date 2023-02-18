package com.janek.simplesns.domain.member.repository;

import com.janek.simplesns.domain.member.entity.MemberNicknameHistory;

import java.util.List;

public interface MemberNicknameHistoryRepository {

    List<MemberNicknameHistory> findAllByMemberId(Long memberId);

    MemberNicknameHistory save(MemberNicknameHistory member);

}
