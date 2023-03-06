package com.janek.simplesns.application.usecase;

import com.janek.simplesns.domain.follow.entity.Follow;
import com.janek.simplesns.domain.follow.service.FollowReadService;
import com.janek.simplesns.domain.post.entity.Post;
import com.janek.simplesns.domain.post.service.PostReadService;
import com.janek.simplesns.util.CursorRequest;
import com.janek.simplesns.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTimelinePostUseCase {

    private final FollowReadService followReadService;

    private final PostReadService postReadService;

    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        List<Long> memberIds = followReadService.getFollowings(memberId).stream().map(Follow::getToMemberId).toList();

        return postReadService.getPosts(memberIds, cursorRequest);
    }

}
