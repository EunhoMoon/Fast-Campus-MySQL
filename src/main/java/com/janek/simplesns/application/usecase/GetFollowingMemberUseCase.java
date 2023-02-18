package com.janek.simplesns.application.usecase;

import com.janek.simplesns.domain.follow.entity.Follow;
import com.janek.simplesns.domain.follow.service.FollowReadService;
import com.janek.simplesns.domain.member.dto.MemberDto;
import com.janek.simplesns.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFollowingMemberUseCase {

    private final MemberReadService memberReadService;

    private final FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId) {
        /**
         * 1. fromMemberId = memberId -> FollowList
         * 2. 1번을 순회하면서 회원 정보를 찾으면 된다.
         */
        List<Long> followings = followReadService.getFollowings(memberId)
                .stream().map(Follow::getToMemberId).toList();

        return memberReadService.getMembers(followings);
    }

}
