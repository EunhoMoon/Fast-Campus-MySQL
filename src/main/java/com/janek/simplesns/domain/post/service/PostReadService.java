package com.janek.simplesns.domain.post.service;

import com.janek.simplesns.domain.post.dto.DailyPostCountRequest;
import com.janek.simplesns.domain.post.dto.DailyPostCountResponse;
import com.janek.simplesns.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {

    private final PostRepository postRepository;

    public List<DailyPostCountResponse> getDailyPostCount(DailyPostCountRequest request) {
        return postRepository.groupByCreatedDate(request);
    }

}
