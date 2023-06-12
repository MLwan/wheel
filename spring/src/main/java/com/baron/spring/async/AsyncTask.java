package com.baron.spring.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask extends AbstractTask {
    @Async
    public void doTaskOne() throws Exception {
        super.doTaskOne();
        System.out.println("任务一AsyncTask，当前线程：" + Thread.currentThread().getName());
    }

    @Async
    public void doTaskTwo() throws Exception {
        super.doTaskTwo();
        System.out.println("任务二AsyncTask，当前线程：" + Thread.currentThread().getName());
    }

    @Async
    public void doTaskThree() throws Exception {
        super.doTaskThree();
        System.out.println("任务三AsyncTask，当前线程：" + Thread.currentThread().getName());
    }
}
