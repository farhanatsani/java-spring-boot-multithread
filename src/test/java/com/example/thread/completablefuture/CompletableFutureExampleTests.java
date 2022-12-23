package com.example.thread.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureExampleTests {

    @Test
    void testSimpleCompletableFuture() {
        System.out.println("start Main thread " + Thread.currentThread().getName());
        CompletableFuture.runAsync(() -> {
            System.out.println("Async thread " + Thread.currentThread().getName());
        });
        System.out.println("end Main thread " + Thread.currentThread().getName());
    }

    @Test
    void testCompletableFutureRunAsync() {
        ExecutorService executor = Executors.newSingleThreadExecutor();



    }

    @Test
    void testCompletableFuture() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();



    }

}
