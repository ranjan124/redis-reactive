package com.shieldteq.redisson;

import com.shieldteq.redisson.config.RedissonConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.redisson.api.RedissonReactiveClient;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    private final RedissonConfig config = new RedissonConfig();
    protected RedissonReactiveClient client;

    @BeforeAll
    public void setup() {
        client = config.getReactiveClient();
    }

    @AfterAll
    public void shutDown() {
        config.getClient().shutdown();
    }


    protected void sleepFor(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
