package com.janek.simplesns.domain.post.repository;

import com.janek.simplesns.domain.post.dto.DailyPostCountRequest;
import com.janek.simplesns.domain.post.dto.DailyPostCountResponse;
import com.janek.simplesns.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepository {

    Post save(Post post);

    List<DailyPostCountResponse> groupByCreatedDate(DailyPostCountRequest request);

    void bulkInsert(List<Post> posts);

    public Page<Post> findAllByMemberId(Long memberId, Pageable pageable);

    List<Post> findAllByMemberIdAndOrderByIdDesc(Long memberId, int size);

    List<Post> findAllByLessThenIdAndMemberIdAndOrderByIdDesc(Long id, Long memberId, int size);

}
