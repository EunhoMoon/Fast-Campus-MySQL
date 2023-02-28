package com.janek.simplesns.application.controller;

import com.janek.simplesns.domain.post.dto.DailyPostCountRequest;
import com.janek.simplesns.domain.post.dto.DailyPostCountResponse;
import com.janek.simplesns.domain.post.dto.PostCommand;
import com.janek.simplesns.domain.post.entity.Post;
import com.janek.simplesns.domain.post.service.PostReadService;
import com.janek.simplesns.domain.post.service.PostWriteService;
import com.janek.simplesns.util.CursorRequest;
import com.janek.simplesns.util.PageCursor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostWriteService postWriteService;

    private final PostReadService postReadService;

    @PostMapping("/")
    public Long create(PostCommand command) {
        return postWriteService.create(command);
    }

    @GetMapping("/daily-post-count")
    public List<DailyPostCountResponse> getDailyPostCount(DailyPostCountRequest request) {
        return postReadService.getDailyPostCount(request);
    }

    @GetMapping("/members/{memberId}")
    public Page<Post> getPosts(@PathVariable Long memberId, Pageable pageable) {
        return postReadService.getPosts(memberId, pageable);
    }

    @GetMapping("/members/{memberId}/by-cursor")
    public PageCursor<Post> getPostsByCursor(@PathVariable Long memberId, CursorRequest request) {
        return postReadService.getPosts(memberId, request);
    }

}
