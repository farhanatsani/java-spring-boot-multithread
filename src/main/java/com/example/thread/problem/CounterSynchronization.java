package com.example.thread.problem;

public class CounterSynchronization {
    private int count = 0;
    public synchronized void increment() {
        this.count = count + 1;
    }
    public int getCount() {
        return count;
    }
}
