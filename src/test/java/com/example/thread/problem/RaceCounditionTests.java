package com.example.thread.problem;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaceCounditionTests {
    @Test
    void testRaceCondition() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CounterRaceCondition counterRaceCondition = new CounterRaceCondition();

        for(int i = 0; i < 1000; i++) {
            executorService.submit(() -> counterRaceCondition.increment());
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        System.out.println("Final count is : " + counterRaceCondition.getCount());
    }
    @Test
    void testSynchronization() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CounterSynchronization counter = new CounterSynchronization();

        for(int i = 0; i < 1000; i++) {
            executorService.submit(() -> counter.increment());
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        System.out.println("Final count is : " + counter.getCount());
    }
    @Test
    void testAtomic() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CounterAtomic counter = new CounterAtomic();

        for(int i = 0; i < 1000; i++) {
            executorService.submit(() -> counter.getAndIncrementCount());
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        System.out.println("Final count is : " + counter.getCount());
    }
}
