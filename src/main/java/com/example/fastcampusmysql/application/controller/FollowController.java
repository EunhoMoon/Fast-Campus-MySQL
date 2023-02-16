package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usacase.CreateFollowMemberUsaCase;
import com.example.fastcampusmysql.application.usacase.GetFollowingMemberUsaCase;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

    private final CreateFollowMemberUsaCase createFollowMemberUsaCase;

    private final GetFollowingMemberUsaCase getFollowingMemberUsaCase;

    @PostMapping("/{fromId}/{toId}")
    public void create(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsaCase.execute(fromId, toId);
    }

    @GetMapping("/members/{fromId}")
    public List<MemberDto> getFollowings(@PathVariable Long fromId) {
        return getFollowingMemberUsaCase.execute(fromId);
    }

}
