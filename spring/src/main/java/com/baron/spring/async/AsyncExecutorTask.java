package com.baron.spring.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncExecutorTask extends AbstractTask {

    @Async("logExecutor")
    public Future<String> doTaskOneCallback() throws Exception {
        super.doTaskOne();
        System.out.println("任务一AsyncExecutorTask，当前线程：" + Thread.currentThread().getName());
        return new AsyncResult<>("任务一完成");
    }



    @Async("logExecutor")
    public Future<String> doTaskTwoCallback() throws Exception {
        super.doTaskTwo();
        System.out.println("任务二AsyncExecutorTask，当前线程：" + Thread.currentThread().getName());
        return new AsyncResult<>("任务二完成");
    }


    @Async("logExecutor")
    public Future<String> doTaskThreeCallback() throws Exception {
        super.doTaskThree();
        System.out.println("任务三AsyncExecutorTask，当前线程：" + Thread.currentThread().getName());
        return new AsyncResult<>("任务三完成");
    }
}
