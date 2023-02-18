package com.janek.simplesns.domain.follow.service;

import com.janek.simplesns.domain.follow.entity.Follow;
import com.janek.simplesns.domain.follow.repository.FollowRepository;
import com.janek.simplesns.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.*;

@Service
@RequiredArgsConstructor
public class FollowWriteService {

    private final FollowRepository followRepository;

    public void create(MemberDto fromMember, MemberDto toMember) {
        isTrue(!fromMember.id().equals(toMember.id()), "From, To 회원이 동일합니다.");

        Follow follow = Follow.builder()
                .fromMemberId(fromMember.id())
                .toMemberId(toMember.id())
                .build();

        followRepository.save(follow);
    }

}
