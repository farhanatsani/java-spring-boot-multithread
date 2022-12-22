package com.example.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCallableFuture implements Callable<String> {

    private AtomicInteger counter = new AtomicInteger(0);
    private String name;

    public MyCallableFuture(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        for(int i = 0; i < 1000; i++) {
            counter.getAndIncrement();
        }
        return name + " " + counter.get();
    }

}
