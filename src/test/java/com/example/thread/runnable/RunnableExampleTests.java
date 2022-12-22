package com.example.thread.runnable;

import org.junit.jupiter.api.Test;

public class RunnableExampleTests {

//    @Test
    void runnableTest() {
        System.out.println("start");
        MyRunnable myRunnable = new MyRunnable();
        myRunnable.run();
        System.out.println("end");
    }

    @Test
    void createThread() {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("Hello from thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        try {
            thread.join();

        } catch (InterruptedException e) {
        }

        System.out.println("Program selesai " + Thread.currentThread().getName());
    }

    @Test
    void threadInterrupted() throws InterruptedException {
        Runnable runnable = () -> {
            for(int i = 0; i < 10; i++) {
                if(Thread.interrupted()) {
                    return;
                }
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    return;
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000);
        thread.interrupt();
        System.out.println("Menunggu Selesai");
        thread.join();
        System.out.println("Program selesai");
    }

    @Test
    void threadName() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Run in thread : " + Thread.currentThread().getName());
        });
        thread.setName("Farhan");
        System.out.println(thread.getState());
        thread.start();
        thread.join();
        System.out.println(thread.getState());
    }

}
