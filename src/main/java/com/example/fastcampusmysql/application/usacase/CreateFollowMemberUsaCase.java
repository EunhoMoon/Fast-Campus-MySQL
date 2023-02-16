package com.example.fastcampusmysql.application.usacase;

import com.example.fastcampusmysql.domain.follow.service.FollowWriteService;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFollowMemberUsaCase {

    private final MemberReadService memberReadService;

    private final FollowWriteService followWriteService;

    public void execute(Long fromMemberId, Long toMemberId) {
        /**
         * 도메인 서비스의 흐름을 제어하는 역할만 수행
         * 1. 입력 받은 memberId로 회원 조회
         * 2. FollowWriteService.create();
         */
        MemberDto fromMember = memberReadService.getMember(fromMemberId);
        MemberDto toMember = memberReadService.getMember(toMemberId);

        followWriteService.create(fromMember, toMember);
    }

}
