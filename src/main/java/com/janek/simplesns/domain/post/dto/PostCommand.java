package com.janek.simplesns.domain.post.dto;

public record PostCommand(
        Long memberId,
        String contents
) {
}
