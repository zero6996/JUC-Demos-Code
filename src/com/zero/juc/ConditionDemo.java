package com.zero.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    // 1=A,2=B,3=C
    private int number = 1;
    private Lock lock = new ReentrantLock();
    // 精准唤醒
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();
    public void print5(){
        lock.lock();
        try{
            // 1. 判断
            while (number != 1){
                c1.await();
            }
            // 工作
            for (int i = 0; i < 5 ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 通知
            // 一定要先改标志位
            number = 2;
            // 精准唤醒下一个线程
            c2.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try{
            // 1. 判断
            while (number != 2){
                c2.await();
            }
            // 工作
            for (int i = 0; i < 10 ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 通知
            // 一定要先改标志位
            number = 3;
            c3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try{
            // 1. 判断
            while (number != 3){
                c3.await();
            }
            // 工作
            for (int i = 0; i < 15 ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 通知
            // 一定要先改标志位
            number = 1;
            c1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}


/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/8 9:16
 * @Description: condition精准唤醒案例，线程之间的顺序调度
 * 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 *  AA打印5次，BB打印10次，CC打印15次
 *  接着
 *  AA打印5次，BB打印10次，CC打印15次
 *  进行10轮
 */

public class ConditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareData.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareData.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareData.print15();
            }
        },"C").start();
    }
}
