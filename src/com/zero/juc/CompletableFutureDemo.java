package com.zero.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/15 15:44
 * @Description: 异步回调
 */

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步调用，无返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> System.out.println(Thread.currentThread().getName() + "\t无返回"));
        completableFuture.get();
        //异步回调
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t completableFuture2");
            int i = 10/0;
            return 1024;
        });

        Integer result = completableFuture2.whenComplete((t, u) -> {
            System.out.println(">>>>t: " + t); // 正常执行
            System.out.println(">>>>u: " + u); // 异常执行
        }).exceptionally(f -> { // 如果出现异常会执行的函数
            System.out.println(">>>>exception:" + f.getMessage());
            return 444;
        }).get();
        System.out.println(result);
    }
}
