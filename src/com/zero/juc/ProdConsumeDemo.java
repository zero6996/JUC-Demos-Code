package com.zero.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 空调类
class Aircondition{
    private int number = 0;
    // 新版写法
    private Lock lock = new ReentrantLock();
    // 使用新版的通知唤醒
    private Condition condition = lock.newCondition();

    // 模拟生产者
    public void increment(){
        lock.lock();
        try{
            // 1. 判断
            while (number != 0){
                condition.await();
            }
            // 2. 工作
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 3. 通知，唤醒其他等待线程
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    // 模拟生产者
    public void decrement(){
        lock.lock();
        try{
            // 1. 判断
            while (number == 0){
                condition.await();
            }
            // 2. 工作
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 3. 通知，唤醒其他等待线程
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    /* 老版写法
    // 模拟生产者
    public synchronized void increment() throws Exception{
        // 1. 判断
        while (number != 0){
            this.wait();
        }
        // 2. 工作
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 3. 通知，唤醒其他等待线程
        this.notifyAll();
    }
    // 模拟消费者
    public synchronized void decrement() throws Exception{
        // 1. 判断
        while (number == 0){
            this.wait();
        }
        // 2. 工作
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 3. 通知
        this.notifyAll();
    }
     */

}

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/7 18:33
 * @Description: 生产者和消费者
 *  题目：两个线程，可以操作初始值为零的一个变量
 *  实现一个线程对该变量+1，一个线程对该变量-1
 *  交替执行10轮，变量初始值为0
 *
 *   1. 高聚低合前提下，线程 操作 资源类
 *   2. 判断/工作/通知
 *   3. 防止多线程的虚假唤醒
 *      使用wait必须注意，中断和虚假唤醒是可能的，故该方法应该在循环中使用
 *
 *   为什么在wait使用if的情况下，变量会大于1或小于0？
 *      由于cpu的调度是不可控的，所以在生产者A生产完后，下一个可能不是消费者执行，
 *      而是生产者B继续执行，如果使用if就会进行判断number!=0，进入wait状态等待，
 *      此时生产者A再次获取时间片，执行代码if判断进入wait状态；这样两个生产者同时
 *      处于wait状态了，后续如果唤醒就会使number++两次，导致值为2。同理消费者也是。
 *      所以要使用while循环判断条件是否满足，才可以交替执行生成和消费。
 *
 *   知识小总结：多线程编程套路(判断/工作/通知)+while判断(防止虚假唤醒)+新版写法(lock+condition)
 *
 */


public class ProdConsumeDemo {
    public static void main(String[] args) throws Exception{
        Aircondition aircondition = new Aircondition();

        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try {
                    Thread.sleep(200);
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"生产者A").start();

        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try {
                    Thread.sleep(300);
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"消费者B").start();

        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try {
                    Thread.sleep(400);
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"生产者C").start();

        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try {
                    Thread.sleep(500);
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"消费者D").start();

    }
}
