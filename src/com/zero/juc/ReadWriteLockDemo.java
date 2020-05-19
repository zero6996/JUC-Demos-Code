package com.zero.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t>>>开始写入"+key);
            // 暂停一定毫秒时间
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t>>>写入成功"+key);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            readWriteLock.writeLock().unlock();
        }
    }
    public void get(String key){
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t>>>开始获取数据"+key);
            // 暂停一定时间
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t>>>成功获取数据"+o);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            readWriteLock.readLock().unlock();
        }
    }
}
/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/11 16:52
 * @Description: 读写锁案例
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源，就不应该有其他线程可以同时对该资源进行读或写操作
 *  小结：
 *   读：共享读
 *   读：排他写
 *   写：排他写
 *
 *  保证了数据一致性
 */

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache cache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int finalI = i;
            new Thread(()->{
                cache.put(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int finalI = i;
            new Thread(()->{
                cache.get(finalI +"");
            },String.valueOf(i)).start();
        }

    }
}
