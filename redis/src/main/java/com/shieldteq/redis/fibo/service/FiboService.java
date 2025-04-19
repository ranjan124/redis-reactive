package com.shieldteq.redis.fibo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FiboService {
    @Cacheable("math:fibo")
    public long fibo(long index) {
        log.info("fibo({})", index);

        return calculateFib(index);
    }

    @Scheduled(fixedRate = 30000)
    @CacheEvict(value = "math:fibo", allEntries = true)
    public void clearCache() {
        System.out.println("Clearing cache fibo");
    }
    @Scheduled(fixedRate = 30000)
    @CacheEvict(value = "math:fib", allEntries = true)
    public void clearFib() {
        System.out.println("Clearing cache fib");
    }

    @CacheEvict("math:fibo")
    public void clearCache(long index) {
        clearFib(index);
    }


    @CacheEvict("math:fib")
    public void clearFib(long index) {
        if (index < 2) {
            return;
        }
        clearFib(index - 1);
    }

    @Cacheable("math:fib")
    public long calculateFib(long index) {
        if (index < 2) {
            return index;
        }
        return calculateFib(index - 1) + calculateFib(index - 2);
    }
}
