package com.janek.simplesns.domain.member.service;

import com.janek.simplesns.domain.member.dto.MemberDto;
import com.janek.simplesns.domain.member.dto.MemberNicknameHistoryDto;
import com.janek.simplesns.domain.member.entity.Member;
import com.janek.simplesns.domain.member.entity.MemberNicknameHistory;
import com.janek.simplesns.domain.member.repository.MemberNicknameHistoryRepository;
import com.janek.simplesns.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberReadService {

    private final MemberRepository memberRepository;

    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public MemberDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return toDto(member);
    }

    public List<MemberDto> getMembers(List<Long> ids) {
        return memberRepository.findAllByIdIn(ids).stream().map(this::toDto).toList();
    }

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId) {
        return memberNicknameHistoryRepository.findAllByMemberId(memberId)
                .stream().map(this::toDto)
                .toList();
    }

    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getEmail(), member.getNickname(), member.getBirthDay());
    }

    private MemberNicknameHistoryDto toDto(MemberNicknameHistory history) {
        return new MemberNicknameHistoryDto(history.getId(), history.getMemberId(), history.getNickname(), history.getCreatedAt());
    }

}
