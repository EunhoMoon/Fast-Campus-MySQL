package com.janek.simplesns.domain.post.repository;

import com.janek.simplesns.domain.post.dto.DailyPostCountRequest;
import com.janek.simplesns.domain.post.dto.DailyPostCountResponse;
import com.janek.simplesns.domain.post.entity.Post;

import java.util.List;

public interface PostRepository {

    Post save(Post post);

    List<DailyPostCountResponse> groupByCreatedDate(DailyPostCountRequest request);

    void bulkInsert(List<Post> posts);

}
