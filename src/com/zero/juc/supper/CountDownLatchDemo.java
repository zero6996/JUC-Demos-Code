package com.zero.juc.supper;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/11 15:14
 * @Description: 减法控制线程执行
 *  模拟：下课教室走人场景，必须等6位同学都走了，班长才允许关门走人。
 *  控制线程顺序
 */

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t班长关门走人");
    }

    private static void closeDoor() {
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName()+"\t班长关门走人");
    }
}
