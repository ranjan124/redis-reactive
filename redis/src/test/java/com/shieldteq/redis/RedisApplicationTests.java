package com.shieldteq.redis;

import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class RedisApplicationTests {
    @Autowired
    private ReactiveStringRedisTemplate redisTemplate;

    @RepeatedTest(5)
    void springDataRedisTest() {
        ReactiveValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        long before = System.currentTimeMillis();
        Flux.range(1, 500_000)
                .flatMap(i -> opsForValue.increment("user:1:visit"))
                .as(StepVerifier::create)
                .expectNextCount(500_000)
                .verifyComplete();
        long after = System.currentTimeMillis();
        System.out.println("Time taken: " + (after - before) + "ms");

    }

}
