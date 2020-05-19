package com.zero.juc;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zero
 * @Description: 题目：三个售票员 > 卖出 > 30张票
 *  如何编写企业级的多线程代码
 *  固定编程套路+模板是什么？
 *  1. 在高内聚低耦合的前提下，线程 > 操作 > 资源类
 *
 *
 * Date: Create in 2020/4/30 20:37
 */

public class SaleTicketDemo01 {

    public static void main(String[] args) { // 主线程，一切程序的入口
        Ticket ticket = new Ticket();
        // 匿名内部类实现
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();
        */
        // lambda表达式实现
        new Thread(() -> {for (int i=1;i<=40;i++) ticket.sale();},"A").start();
        new Thread(() -> {for (int i=1;i<=40;i++) ticket.sale();},"B").start();
        new Thread(() -> {for (int i=1;i<=40;i++) ticket.sale();},"C").start();

    }
}


/**
 * 资源类 = 实例变量+实例方法
 */
class Ticket{

    // 模拟30张票
    private int ticketNumber = 30;
    Lock lock = new ReentrantLock();

    // 操作 = 卖票
    public void sale(){
        lock.lock();
        try{
            if (ticketNumber > 0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(ticketNumber--)+"\t还剩下："+ticketNumber);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}