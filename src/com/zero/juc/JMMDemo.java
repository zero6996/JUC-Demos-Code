package com.zero.juc;

class myNumber{
     int number = 10;
    public void addNumber(){
        this.number = 2020;
    }
}

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/8 18:53
 * @Description: JMM代码示例
 *   JMM的可见性问题(通知机制)
 *    使用volatile解决
 */

public class JMMDemo {
    public static void main(String[] args) {
        myNumber myNumber = new myNumber();

        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.addNumber();
            System.out.println(Thread.currentThread().getName()+"\t update number,number value:"+myNumber.number);
        },"AAA").start();

        // 由与JMM的可见性问题，AAA线程修改了number变量，对于其他线程不可见，故main线程while一直处于死循环
        while (myNumber.number==10){
            // 需要一种通知机制告诉main线程，number的值已经修改过了

        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over");

    }
}
