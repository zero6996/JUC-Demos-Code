package com.zero.juc.interview;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: zero
 * Date: Create in 2020/5/4 16:08
 * @Description:
 *  ArrayList为什么线程不安全，请从以下4个维度解释
 *  1. 故障现象
 *      java.util.ConcurrentModificationException
 *  2. 导致原因
 *      多线程情况下同时争抢一个资源类且没加锁
 *  3. 解决方法
 *     3.1 使用java自带的new Vector<>();
 *     3.2 使用Collections工具类的synchronizedList(new ArrayList<>())
 *     3.3 使用juc包下的new CopyOnWriteArrayList<>(); 写时复制技术
 *  4. 优化建议(同样的错误不犯第2次)
 */

public class arrayListNotSafe {
    public static void main(String[] args) {
        mapNotSafe();
    }

    private static void mapNotSafe() {
        Map<String,String> map = new ConcurrentHashMap();//new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>(new HashSet<>());//new HashSet<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();// Collections.synchronizedList(new ArrayList<>());//new Vector<>();//new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
