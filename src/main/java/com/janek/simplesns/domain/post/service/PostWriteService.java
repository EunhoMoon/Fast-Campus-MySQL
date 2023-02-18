package com.janek.simplesns.domain.post.service;

import com.janek.simplesns.domain.post.dto.PostCommand;
import com.janek.simplesns.domain.post.entity.Post;
import com.janek.simplesns.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostWriteService {

    private final PostRepository postRepository;

    public Long create(PostCommand command) {
        Post post = Post.builder()
                .memberId(command.memberId())
                .contents(command.contents())
                .build();

        return postRepository.save(post).getId();
    }

}
