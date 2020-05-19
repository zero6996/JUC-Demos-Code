package com.zero.juc;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/11 21:10
 * @Description:
 */

public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception{
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        /*
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
//        System.out.println(queue.add("x"));
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());*/
//        System.out.println(queue.add("a"));
//        System.out.println(queue.add("a"));
        // 检查队首元素
//        System.out.println(queue.element());
        /*
        // 插入元素，返回成功布尔值
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("x"));
        // 安全移除元素
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        */
        /*
        // 阻塞队列已满则一直阻塞，直到有空位
        queue.put("a");
        queue.put("a");
        queue.put("a");
//        queue.put("a");
        // 阻塞队列为空则一直阻塞，直到有元素
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        */

        System.out.println(queue.offer("a" ));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("x", 3,TimeUnit.SECONDS));

    }
}
