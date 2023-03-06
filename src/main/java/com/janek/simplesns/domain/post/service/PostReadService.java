package com.janek.simplesns.domain.post.service;

import com.janek.simplesns.domain.post.dto.DailyPostCountRequest;
import com.janek.simplesns.domain.post.dto.DailyPostCountResponse;
import com.janek.simplesns.domain.post.entity.Post;
import com.janek.simplesns.domain.post.repository.PostRepository;
import com.janek.simplesns.util.CursorRequest;
import com.janek.simplesns.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {

    private final PostRepository postRepository;

    public List<DailyPostCountResponse> getDailyPostCount(DailyPostCountRequest request) {
        return postRepository.groupByCreatedDate(request);
    }

    public Page<Post> getPosts(Long memberId, Pageable pageable) {
        return postRepository.findAllByMemberId(memberId, pageable);
    }

    public PageCursor<Post> getPosts(Long memberId, CursorRequest request) {
        List<Post> posts = findAllBy(memberId, request);
        long nextKey = getNextKey(posts);

        return new PageCursor<>(request.next(nextKey), posts);
    }

    public PageCursor<Post> getPosts(List<Long> memberIds, CursorRequest request) {
        List<Post> posts = findAllBy(memberIds, request);
        long nextKey = getNextKey(posts);

        return new PageCursor<>(request.next(nextKey), posts);
    }

    private List<Post> findAllBy(Long memberId, CursorRequest request) {
        if (request.hasKey()) {
            return postRepository.findAllByLessThenIdAndMemberIdAndOrderByIdDesc(request.key(), memberId, request.size());
        }

        return postRepository.findAllByMemberIdAndOrderByIdDesc(memberId, request.size());
    }

    private List<Post> findAllBy(List<Long> memberIds, CursorRequest request) {
        if (request.hasKey()) {
            return postRepository.findAllByLessThenIdAndMemberIdAndOrderByIdDesc(request.key(), memberIds, request.size());
        }

        return postRepository.findAllByMemberIdAndOrderByIdDesc(memberIds, request.size());
    }

    private static long getNextKey(List<Post> posts) {
        return posts.stream().mapToLong(Post::getId).min().orElse(CursorRequest.NONE_KEY);
    }

}
