package com.example.thread.completablefuture;

import com.example.thread.callable.MyCallableFuture;
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

        for(int i = 0; i <= 10; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> {
                try {
                    String name = "test-" + finalI;
                    String r = new MyCallableFuture(name).call();
                    System.out.println(r);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, executor);
        }

    }

    @Test
    void testCompletableFuture() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        List<CompletableFuture<String>> results = new ArrayList<>();
        for(int i = 0; i <= 10; i++) {
            int finalI = i;
            CompletableFuture<String> cfAsync = CompletableFuture.supplyAsync(() -> {
                String name = "test-" + finalI;
                String resultCall;
                try {
                    resultCall = new MyCallableFuture(name).call();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return resultCall;
            }, executor);
            results.add(cfAsync);
        }

        for(CompletableFuture cf: results) {
            String result = (String) cf.get();
            System.out.println(result);
        }

    }

    @Test
    void testCompletedFuture() throws ExecutionException, InterruptedException {
        System.out.println("start " +Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        String hello = "HelloWorld";

        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("")
                .thenApplyAsync(s -> {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return hello;
                });

        String value = completableFuture.get();

        System.out.println(Thread.currentThread().getName());
        System.out.println(value);
        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("elapsed time : " + elapsed + " seconds");
    }

}
