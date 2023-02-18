package com.janek.simplesns.application.usecase;

import com.janek.simplesns.domain.follow.service.FollowWriteService;
import com.janek.simplesns.domain.member.dto.MemberDto;
import com.janek.simplesns.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFollowMemberUseCase {

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
