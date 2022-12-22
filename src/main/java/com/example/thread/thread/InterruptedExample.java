package com.example.thread.thread;

public class InterruptedExample {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for(int i = 0; i <= 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    break;
                }
            }
        };
        Thread t = new Thread(runnable);
        t.start();
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
    }
}
