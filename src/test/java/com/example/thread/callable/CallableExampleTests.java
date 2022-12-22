package com.example.thread.callable;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableExampleTests {

    @Test
    void testCallable() {
        MyCallable myCallable = new MyCallable("Farhan");

        ExecutorService exec = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 10; i++) {
            exec.submit(myCallable);
        }
        exec.shutdown();
    }

}
