package com.shieldteq.redis.fibo.controller;

import com.shieldteq.redis.fibo.service.FiboService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fibo")
public class FiboController {
    private final FiboService fiboService;

    @GetMapping("/{index}")
    public Mono<Long> fibo(@PathVariable Long index) {
        return Mono.fromSupplier(() -> fiboService.fibo(index));
    }

    @GetMapping("/{index}/clear")
    public Mono<Void> fiboClear(@PathVariable Long index) {
        return Mono.fromRunnable(() -> fiboService.clearCache(index));
    }
}
