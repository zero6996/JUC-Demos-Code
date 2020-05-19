package com.zero.juc.supper;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/11 16:04
 * @Description:　循环屏障demo，加法控制线程执行，7龙珠案例
 *
 */

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(7,()-> System.out.println(">>>7龙珠召唤神龙<<<"));
        for (int i = 1; i <= 7; i++) {
            final int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t收集到第："+ finalI +"颗龙珠");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
