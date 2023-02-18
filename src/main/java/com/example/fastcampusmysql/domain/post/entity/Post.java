package com.example.fastcampusmysql.domain.post.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.LocalDateTime.*;
import static java.util.Objects.*;

@Getter
public class Post {

    private final Long id;

    private final Long memberId;

    private final String contents;

    private final LocalDate createdDate;

    private final LocalDateTime createdAt;

    @Builder
    public Post(Long id, Long memberId, String contents, LocalDate createdDate, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = requireNonNull(memberId);
        this.contents = requireNonNull(contents);
        this.createdDate = (createdDate == null) ? LocalDate.now() : createdDate;
        this.createdAt = (createdAt == null) ? now() : createdAt;
    }
}
