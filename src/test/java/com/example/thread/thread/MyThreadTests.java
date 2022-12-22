package com.example.thread.thread;

import org.junit.jupiter.api.Test;

public class MyThreadTests {

    @Test
    void threadTest() {
        System.out.println(Thread.currentThread().getName());
        Thread thread = new Thread(() -> {
           for(int i = 0; i <= 10; i++) {
               System.out.println(Thread.currentThread().getName() + " " + i);
           }
        });
        thread.setName("adira");
        thread.start();
    }

    @Test
    void runnableTest() {
        Runnable runnable = () -> {
            for(int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        };
        new Thread(runnable).start();
    }

    @Test
    void sleepTest() {
        Runnable runnable = () -> {
            for(int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(1_00L);
                } catch (InterruptedException e) {}
            }
        };
        new Thread(runnable).start();
        try {
            Thread.sleep(2_000L);
        } catch (InterruptedException e) {}
    }

    @Test
    void joinTest() {
        System.out.println("start " +Thread.currentThread().getName());
        Runnable runnable = () -> {
            for(int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        };
        Thread t = new Thread(runnable);
        t.setName("adira");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {}
        System.out.println("end " + Thread.currentThread().getName());
    }

    void interruptedTest() {
        System.out.println("start " +Thread.currentThread().getName());
        Runnable runnable = () -> {
            for(int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        };
        Thread t = new Thread(runnable);
        t.setName("adira");
        t.start();
    }
}
