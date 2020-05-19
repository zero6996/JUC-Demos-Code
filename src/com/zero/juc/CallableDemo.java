package com.zero.juc;

import java.util.concurrent.*;


class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("come in call Callable()");
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/8 9:50
 * @Description:
 * Runnable和Callable的区别
 *  1. 方法名称不同，run和call
 *  2. 异常不同，run异常自己处理，call可能抛异常
 *  3. 返回值，run无返回值，call有返回值
 *
 *  Callable一般用FutureTask作为中间人，然后放到Thread线程中启动
 *  1. get方法一般放在最后一行
 */

public class CallableDemo{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 借助FutureTask执行Callable
        FutureTask<Integer> task = new FutureTask(new MyThread());
        new Thread(task,"A").start();
        new Thread(task,"B").start();
        System.out.println(task.get());
        System.out.println(Thread.currentThread().getName()+"计算完成");
        // 借助线程池执行
        ExecutorService pool = Executors.newCachedThreadPool();
//        pool.submit(()->System.out.println("runnbale"));
        Future<Integer> future = pool.submit(new MyThread());
        System.out.println(future.get());
        pool.shutdown();
    }
}
