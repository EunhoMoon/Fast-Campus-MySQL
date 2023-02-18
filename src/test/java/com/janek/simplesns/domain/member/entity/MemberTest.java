package com.janek.simplesns.domain.member.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.janek.simplesns.util.MemberFixtureFactory.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    void test() {
        //given
//        LongStream.range(0, 10)
//                .mapToObj(MemberFixtureFactory::create)
//                .forEach(member -> System.out.println(member.getNickname()));
        Member member = create();
        String expected = "pnu";

        // when
        member.changeNickname(expected);

        // then
        assertEquals(expected, member.getNickname());
    }

    @Test
    @DisplayName("회원의 닉네임은 열 글자를 초과할 수 없다.")
    void test2() {
        //given
        Member member = create();
        String overMaxLengthNam = "abadacadabrahahahaharry!";

        // then
        assertThrows(IllegalArgumentException.class, () -> member.changeNickname(overMaxLengthNam));
    }

}