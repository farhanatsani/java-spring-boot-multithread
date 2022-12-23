package com.example.thread.problem;

public class CounterRaceCondition {
    private int count = 0;
    public void increment() {
        this.count = count + 1;
    }
    public int getCount() {
        return count;
    }
}
