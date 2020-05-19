package com.zero.juc.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/10 10:28
 * @Description: 面试题
 *  两个线程，一个线程打印1-26，另一个线程打印A-Z
 *  要求打印顺序为：1A2B3C.....26Z
 *   用线程间通信
 *   ASCII码，A=65，65+25=90，得Z=90，故A-Z=65-90
 *   (char)65=A
 */

public class printNumberAndtheAlphabet {
    public static void main(String[] args) {
        printData data = new printData();
        new Thread(()->{
            for (int i = 1; i <= 26 ; i++) {
                data.printNumber();
            }
        },"打印数字线程").start();
        new Thread(()->{
            for (int i = 1; i <= 26 ; i++) {
                data.printAlphabet();
            }
        },"打印字母线程").start();
    }
}

class printData{
    int Number = 1;
    int ASCIINumber = 65;
    int flag = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void printNumber(){
        lock.lock();
        try{
            while (flag != 0){
                condition.await();
            }
            if (Number<=26) System.out.println(Thread.currentThread().getName()+"\t"+Number);
            Number++;
            flag++;
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    public void printAlphabet(){
        lock.lock();
        try{
            while (flag==0){
                condition.await();
            }
            if (ASCIINumber<=90) System.out.println(Thread.currentThread().getName()+"\t"+(char)ASCIINumber);
            ASCIINumber++;
            flag--;
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
