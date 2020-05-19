package com.zero.juc.supper;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/11 16:22
 * @Description: 争车位案例，控制多线程的并发数量
 */

public class SemaphoreDemo {
    public static void main(String[] args) {
        // 模拟资源类，有3个空车位
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    // 占用
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢到了车位");
                    TimeUnit.SECONDS.sleep(3);
                    // 释放
                    System.out.println(Thread.currentThread().getName()+"\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
