package com.example.thread.problem;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomic {
    private AtomicInteger count = new AtomicInteger(0);
    public int getAndIncrementCount() {
        return count.incrementAndGet();
    }
    public int getCount() {
        return count.get();
    }
}
