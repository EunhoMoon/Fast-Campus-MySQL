package com.example.fastcampusmysql.domain.post.repository;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountResponse;
import com.example.fastcampusmysql.domain.post.entity.Post;

import java.util.List;

public interface PostRepository {

    Post save(Post post);

    List<DailyPostCountResponse> groupByCreatedDate(DailyPostCountRequest request);

    void bulkInsert(List<Post> posts);

}
