package com.example.fastcampusmysql.domain.post;

import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import com.example.fastcampusmysql.util.PostFixtureFactory;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class PostBulkInsertTest {

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("")
    void bulkInsert() {
        EasyRandom easyRandom = PostFixtureFactory.get(
                2L,
                LocalDate.of(1970, 1, 1),
                LocalDate.of(2022, 12, 31)
        );

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int value = 200 * 10_000;
        List<Post> posts = IntStream.range(0, value)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();
        stopWatch.stop();
        System.out.println("객체 생성 소요 시간 = " + stopWatch.getTotalTimeSeconds());

        StopWatch queryStropWatch = new StopWatch();
        queryStropWatch.start();
        postRepository.bulkInsert(posts);
        queryStropWatch.stop();
        System.out.println("insert 소요 시간 = " + queryStropWatch.getTotalTimeSeconds());
    }

}
