package com.jeyson.tools.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: liujishuai
 * @Time: 2018/2/28 10:45
 * @Description:
 */
public class ThreadUtils {
    public static ThreadFactory newThreadFactory(final String threadName) {
        return new ThreadFactory() {
            AtomicInteger atomicInteger = new AtomicInteger(0);
            public Thread newThread(Runnable r) {
                return new Thread(r, threadName + atomicInteger.getAndIncrement());
            }
        };
    }

    public static void main(String[] args) {
         final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10000), ThreadUtils.newThreadFactory("Jeyson-Thread-pool-"), new ThreadPoolExecutor.CallerRunsPolicy());
         for(int i=0;i<3;i++){
             threadPool.execute(new Runnable() {
                 public void run() {
                     try {
                         Thread.sleep(500L);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     System.out.println("线程开始："+Thread.currentThread().getName());
                 }
             });
         }

    }
}
