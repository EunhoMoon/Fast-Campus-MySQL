package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcMemberRepository implements MemberRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final String TABLE = "member";

    @Override
    public Member save(Member member) {
        if (member.getId() == null) {
            return insert(member);
        }
        return update(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = String.format("SELECT * FROM %s WHERE id = :id", TABLE);
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        RowMapper<Member> rowMapper = (rs, rowNum) -> Member.builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .nickname(rs.getString("nickname"))
                .birthDay(rs.getObject("birthday", LocalDate.class))
                .createdAt(rs.getObject("createdAt", LocalDateTime.class))
                .build();

        Member member = namedJdbcTemplate.queryForObject(sql, param, rowMapper);

        return Optional.ofNullable(member);
    }

    private Member insert(Member member) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedJdbcTemplate.getJdbcTemplate())
                .withTableName("Member")
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        Long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return Member.builder()
                .id(id)
                .email(member.getEmail())
                .nickname(member.getNickname())
                .birthDay(member.getBirthDay())
                .createdAt(member.getCreatedAt())
                .build();
    }

    private Member update(Member member) {
        // TODO : implement
        return member;
    }

}
