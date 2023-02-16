package com.example.fastcampusmysql.domain.follow.repository;

import com.example.fastcampusmysql.domain.follow.entity.Follow;

import java.util.List;

public interface FollowRepository {

    Follow save(Follow follow);

    public List<Follow> findAllByFromMemberId(Long memberId);

}
