package com.janek.simplesns.application.controller;

import com.janek.simplesns.domain.post.dto.DailyPostCountRequest;
import com.janek.simplesns.domain.post.dto.DailyPostCountResponse;
import com.janek.simplesns.domain.post.dto.PostCommand;
import com.janek.simplesns.domain.post.service.PostReadService;
import com.janek.simplesns.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

}
