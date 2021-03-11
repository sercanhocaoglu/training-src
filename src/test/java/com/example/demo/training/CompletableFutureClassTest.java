package com.example.demo.training;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompletableFutureClassTest {

    @Autowired
    private CompletableFutureClass completableFutureClass;

    @Test
    void execute() throws ExecutionException, InterruptedException {
        Instant start = Instant.now();
        completableFutureClass.executeAsync();
        Instant finish = Instant.now();
        System.out.println(Duration.between(start, finish).toMillis());

        Instant start2 = Instant.now();
        completableFutureClass.executeSync();
        Instant finish2 = Instant.now();
        System.out.println(Duration.between(start2, finish2).toMillis());
    }
}