package com.example.fastcampusmysql.domain.post.repository;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountResponse;
import com.example.fastcampusmysql.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JdbcPostRepository implements PostRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String TABLE = "Post";

    private static final RowMapper<DailyPostCountResponse> DAILY_POST_COUNT_MAPPER = (rs, rowNum) ->
        new DailyPostCountResponse(
                rs.getLong("memberId"),
                rs.getObject("createdDate", LocalDate.class),
                rs.getLong("count")
        );

    public Post save(Post post) {
        if (post.getId() == null) {
            return insert(post);
        }
        throw new UnsupportedOperationException("Post는 갱신을 지원하지 않습니다.");
    }

    public List<DailyPostCountResponse> groupByCreatedDate(DailyPostCountRequest request) {
        String sql = String.format("""
                SELECT createdDate, memberId, count(id) AS count
                FROM %s
                WHERE memberId = :memberId AND createdDate between :firstDate AND :lastDate
                GROUP BY memberId, createdDate            
                """, TABLE);
        log.info("request={}", request);
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(request);
        return namedParameterJdbcTemplate.query(sql, params, DAILY_POST_COUNT_MAPPER);
    }

    public void bulkInsert(List<Post> posts) {
        String sql = String.format("""
                INSERT INTO %s (
                    memberId, contents, createdDate, createdAt
                ) VALUES (
                    :memberId, :contents, :createdDate, :createdAt
                )
                """, TABLE);

        SqlParameterSource[] params = posts.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }

    private Post insert(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(post);
        long id = jdbcInsert.executeAndReturnKey(params).longValue();

        return Post.builder()
                .id(id)
                .memberId(post.getMemberId())
                .contents(post.getContents())
                .createdDate(post.getCreatedDate())
                .createdAt(post.getCreatedAt())
                .build();
    }

}
