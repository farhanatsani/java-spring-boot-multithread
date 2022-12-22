package com.example.thread.callable;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Object> {

    private String name;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public Object call() throws Exception {
        for(int i = 0; i <= 10; i++) {
            System.out.println(name + " : " + i);
        }
        return name;
    }

}
