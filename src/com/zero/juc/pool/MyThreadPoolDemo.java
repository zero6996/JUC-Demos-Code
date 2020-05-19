package com.zero.juc.pool;

import java.util.concurrent.*;

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/13 9:40
 * @Description:
 */



public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),
                2,TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.AbortPolicy());
        try{
            for (int i = 1; i <= 10; i++) { // RejectedExecutionException
                int finalI = i;
                threadPool.execute(()-> System.out.println(Thread.currentThread().getName()+"\t 办理业务"+ finalI));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }
    }

    private static void initPool() {
        // 一池5个工作线程，类似一个银行有5个受理窗口
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 一池1个工作线程，类似一个银行有1个受理窗口
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 自动扩容,一池n工作线程
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try{
            // 模拟有10个顾客来银行办理业务，目前池子里有5个工作人员提供服务
            for (int i = 1; i <= 100; i++) {
//                TimeUnit.SECONDS.sleep(1);
                int finalI = i;
                threadPool.execute(()-> System.out.println(Thread.currentThread().getName()+"\t 办理业务"+ finalI));
            }
            for (int i = 1; i <= 100; i++) {
//                TimeUnit.SECONDS.sleep(1);
                int finalI = i;
                threadPool.execute(()-> System.out.println(Thread.currentThread().getName()+"\t 办理业务"+ finalI));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }
    }
}
