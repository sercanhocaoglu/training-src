package com.example.demo.training;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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


    @Test
    void myrunnable_start_thread() {

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    @Test
    void func() {

        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used Memory before: " + usedMemoryBefore);
        List<String> arr = new ArrayList<>();
        for (long i = 0; i < 1000000 * 100; i++) {
            arr.add("comolokko");
        }
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory increased: " + (usedMemoryAfter - usedMemoryBefore));
        //Used Memory before: 82132496
        //Memory increased: 18204144
        //Memory increased: 841580952
    }

    @Test
    void stream_test() {
        var arrList = new ArrayList<Integer>();
        for (int i = 0; i < 100*100*100; i++) {
            arrList.add(i);
        }
        Instant start = Instant.now();
        arrList.stream().filter(e -> e % 2 == 0).map(Object::toString).forEach(e->e.toUpperCase());
        Instant finish = Instant.now();

        Instant start2 = Instant.now();
        arrList.parallelStream().filter(e -> e % 2 == 0).map(Object::toString).forEach(e->e.toUpperCase());
        Instant finish2 = Instant.now();

        System.out.println("Duration-1: " + Duration.between(start, finish).toMillis() + " ms");
        System.out.println("Duration-2: " + Duration.between(start2, finish2).toMillis() + " ms");
    }
}