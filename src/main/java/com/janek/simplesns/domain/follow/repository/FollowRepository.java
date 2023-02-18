package com.janek.simplesns.domain.follow.repository;

import com.janek.simplesns.domain.follow.entity.Follow;

import java.util.List;

public interface FollowRepository {

    Follow save(Follow follow);

    public List<Follow> findAllByFromMemberId(Long memberId);

}
