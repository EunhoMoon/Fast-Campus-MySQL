package com.janek.simplesns.application.controller;

import com.janek.simplesns.application.usecase.CreateFollowMemberUseCase;
import com.janek.simplesns.application.usecase.GetFollowingMemberUseCase;
import com.janek.simplesns.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

    private final CreateFollowMemberUseCase createFollowMemberUsaCase;

    private final GetFollowingMemberUseCase getFollowingMemberUsaCase;

    @PostMapping("/{fromId}/{toId}")
    public void create(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsaCase.execute(fromId, toId);
    }

    @GetMapping("/members/{fromId}")
    public List<MemberDto> getFollowings(@PathVariable Long fromId) {
        return getFollowingMemberUsaCase.execute(fromId);
    }

}
