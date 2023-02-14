package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberWriteService {

    private final MemberRepository memberRepository;

    public Member register(RegisterMemberCommand command) {
        /*
            목표 - 회원 정보(이메일, 닉네임, 생년월일) 등록
                - 닉네임을 10자를 넘길 수 없다.
            파라미터 - memberRegisterCommand

            val member = Member.of(memberRegisterCommand)
            memberRepository.save(member)
         */
        Member member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthDay(command.birthDay())
                .build();

        return memberRepository.save(member);
    }

}
