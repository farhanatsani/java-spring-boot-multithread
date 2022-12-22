package com.example.thread.thread;

public class SleepExample {
    public static void main(String[] args) throws InterruptedException {
        // sleep for 1000 millisecond (1 second)
        Thread.sleep(1000L);
        System.out.println("Thread finish");
    }
}
