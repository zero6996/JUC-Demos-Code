package com.zero.juc.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

// 这是一个递归任务,继承后可以实现递归(自己调自己)调用的任务
class MyTask extends RecursiveTask<Integer>{
    private static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin,int end){
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        // 拆分为10个一组
        if ((end-begin) <= ADJUST_VALUE){
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        }else {
            // 获取中间值
            int middle = (end + begin) / 2;
            // 新建两个任务，分配任务需求，一人处理一半
            MyTask task01 = new MyTask(begin,middle);
            MyTask task02 = new MyTask(middle + 1,end);
            // 继续分支
            task01.fork();
            task02.fork();
            // 合并拆分任务的结果值返回
            result = task01.join() + task02.join();
        }

        return result;
    }
}

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/15 9:42
 * @Description: 分支合并框架
 *
 *  ForkJoinPool
 *  ForkJoinTask
 *  RecursiveTask
 */

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建任务
        MyTask myTask = new MyTask(0,100);
        // 创建连接池
        ForkJoinPool threadPool = new ForkJoinPool();
        // 提交任务
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);
        // 汇总结果并输出
        System.out.println(forkJoinTask.get());
        // 关闭连接池
        threadPool.shutdown();
    }
}
