package com.baron.spring.async.testmain;

import com.baron.spring.async.AsyncCallBackTask;
import com.baron.spring.async.AsyncExecutorTask;
import com.baron.spring.async.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.tomcat.jni.Time.sleep;


@RestController
public class AsyncExecutorTaskTest {
    @Autowired
    AsyncTask asyncTask;

    @Autowired
    AsyncExecutorTask asyncExecutorTask;

    @Autowired
    AsyncCallBackTask asyncCallBackTask;
/*
***************************
APPLICATION FAILED TO START
***************************

Description:

Field testExecutor in com.baron.spring.async.testmain.AsyncExecutorTaskTest required a single bean, but 3 were found:
	- logExecutor: defined by method 'asyncServiceExecutor' in class path resource [com/baron/spring/async/config/TaskConfiguration.class]
	- emailExecutor: defined by method 'asyncServiceEmailExecutor' in class path resource [com/baron/spring/async/config/TaskConfiguration.class]
	- reloadExecutor: defined by method 'asyncServiceReloadExecutor' in class path resource [com/baron/spring/async/config/TaskConfiguration.class]


Action:

Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed

 */
    //reloadExecutor 与线程池名称相同，否则报上述错误
    @Autowired
    private ThreadPoolTaskExecutor reloadExecutor;
    @Autowired
    private ThreadPoolTaskExecutor emailExecutor;

    @RequestMapping("/testAsyncExecutorTask")
    private void testAsyncExecutorTask() throws Exception {

        asyncExecutorTask.doTaskOneCallback();
        asyncExecutorTask.doTaskTwoCallback();
        asyncExecutorTask.doTaskThreeCallback();
        sleep(30 * 1000L);
    }

    @RequestMapping("/testTpukAsyncTask")
    private void testTpukAsyncTask() {

        //开启一个线程
        reloadExecutor.execute(() -> {
            try {
                System.out.println("任务一，当前线程：" + Thread.currentThread().getName());
//                asyncTask.doTaskOne();
//                asyncTask.doTaskTwo();
//                asyncTask.doTaskOne();
//                asyncCallBackTask.doTaskOneCallback();
//                asyncCallBackTask.doTaskTwoCallback();
//                asyncCallBackTask.doTaskThreeCallback();
                for (int i = 0; i < 3 ; i++) {
                    //开启一个线程
                    //这个线程是怎么绑定到taskExecutor线程池的？
                    asyncCallBackTask.doTaskOneCallback();
                    asyncExecutorTask.doTaskOneCallback();
                    asyncTask.doTaskOne();
                    System.out.println("for Task "+ i +"，当前线程：" + Thread.currentThread().getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
