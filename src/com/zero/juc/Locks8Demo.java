package com.zero.juc;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/7 10:36
 * @Description: 八锁
 *  八锁示例
 *  1. 标准访问，请问先打印邮件还是短信
 *      邮件>短信
 *  2. 在邮件方法中暂停4秒，请问先打印邮件还是短信？
 *      邮件>短信
 *      1和2小结：
 *          一个对象里面如果有多个synchronized方法，某一时刻内，只要一个线程去调用其中的一个synchronized方法
            其他线程都只能等待，换言之，某一时刻内只能有唯一一个线程去访问这些synchronized方法
            “锁的是当前对象this，被锁定后，其他线程都不能进入到当前对象的其他synchronized方法”
 *  3. 新增普通sayHello方法，先打印邮件还是hello
 *      hello>邮件
 *      3小结：普通方法与同步锁无关，不会竞争资源
 *  4. 两部手机，请问先打印邮件还是短信？
 *     短信>邮件
 *     4小结：不同对象也不是同一把锁，不会竞争资源
 *  5. 两个静态同步方法，同一部手机，请问先打印邮件还是短信？
 *     邮件>短信
 *  6. 两个静态同步方法，两部手机，请问先打印邮件还是短信？
 *     邮件>短信
 *     5和6小结：静态同步锁=全局锁
 *          所有的非静态同步方法用的都是同一把锁---实例对象本身(this)
 *          synchronized实现同步的基础：Java中的每一个对象都可以作为锁
 *          具体表现为以下3种形式
 *          对于普通同步方法，锁是当前实例对象(this)
 *          对于同步方法块，锁的是synchronized括号里配置的对象
 *          对于静态同步方法，锁的是当前类的Class对象(全局锁)
 *
 *
 *  7. 1个静态同步方法，1个普通同步方法，同一部手机，请问先打印邮件还是短信？
 *     短信>邮件
 *        7小结：静态同步方法和普通同步方法 “锁”的对象不冲突
 *  8. 1个静态同步方法，1个普通同步方法，两部手机，请问先打印邮件还是短信？
 *     短信>邮件
 *        8小结：同上，锁的对象不冲突
 *
 *   非静态同步方法：
 *      当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须释放锁。
 *      也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待锁释放后才能获取锁，
 *      其他实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同锁，
 *      所以无需等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。
 *   静态同步方法：
 *      所有的静态同步方法用的是同一把锁---类对象本身(Class)
 *      这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
 *      但是一旦一个静态同步方法获取锁后，其他的当前类对象的所有静态同步方法都必须等待该方法释放锁后才能获取锁，
 *      不管是同一个实例对象还是不同实例对象，只要它们是同一个类的实例对象，那么它们的静态同步方法就是同一把锁。
 */

public class Locks8Demo {
    public static void main(String[] args) throws Exception {

        Phone phone = new Phone();
        Phone phone2 = new Phone();


        new Thread(()->{
            try {
                phone.sendEmail();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"A").start();

        Thread.sleep(100);

        new Thread(()->{
            try {
                phone.sendSMS();
//                phone.sayHello();
//                phone2.sendSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"B").start();

    }
}

class Phone{
    public  static   synchronized void sendEmail() throws Exception{
        TimeUnit.SECONDS.sleep(2);
        System.out.println("----->sendEmail<");
    }
    public  synchronized void sendSMS() throws Exception{
        System.out.println("----->sendSMS<");
    }

    public void sayHello(){
        System.out.println("----->sayHello");
    }
}