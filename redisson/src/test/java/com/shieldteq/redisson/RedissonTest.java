package com.shieldteq.redisson;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucketReactive;
import org.redisson.client.codec.StringCodec;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class RedissonTest extends BaseTest {
    @Test
    public void keyValueTest() {
        RBucketReactive<String> bucket = client.getBucket("user:1:name", StringCodec.INSTANCE);
        Mono<Void> set = bucket.set("John");
        Mono<Void> get = bucket.get().doOnNext(System.out::println).then();
        StepVerifier.create(set.then(get))
                .verifyComplete();


    }

    @Test
    public void keyValueExpireTest() {
        RBucketReactive<String> bucket = client.getBucket("user:1:name", StringCodec.INSTANCE);
        Mono<Void> set = bucket.set("John", Duration.ofSeconds(10));
        Mono<Void> get = bucket.get().doOnNext(System.out::println).then();
        StepVerifier.create(set.then(get))
                .verifyComplete();
        sleepFor(2);
        sleepFor(1);

    }

}
