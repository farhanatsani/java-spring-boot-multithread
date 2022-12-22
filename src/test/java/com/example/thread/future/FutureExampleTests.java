package com.example.thread.future;

import com.example.thread.callable.MyCallable;
import com.example.thread.callable.MyCallableFuture;
import com.example.thread.problem.CounterAtomic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;

public class FutureExampleTests {

    @Test
    void futureTest() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            Thread.sleep(5000);
            return "Hallo";
        });

        while(!future.isDone()) {
            System.out.println("Waiting result");
            Thread.sleep(1000);
        }

        System.out.println(future.get());
    }

    @Test
    void futureCancelTest() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            Thread.sleep(5000);
            return "Hallo";
        });
        Thread.sleep(2000);
        if(1 == 1) {
            future.cancel(true);
        }
        Assertions.assertTrue(future.isCancelled());
    }

    @Test
    void futureInvokeAll() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        List<MyCallableFuture> myCallables = List.of(
                new MyCallableFuture("callable1"),
                new MyCallableFuture("callable2"),
                new MyCallableFuture("callable3"),
                new MyCallableFuture("callable4"),
                new MyCallableFuture("callable5")
        );

        List<Future<String>> futures = executor.invokeAll(myCallables);
        for(Future<String> future: futures) {
            String result = future.get();
            System.out.println(result);
        }

    }
}
