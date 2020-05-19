package com.zero.juc.stream;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/15 14:37
 * @Description:
 */

public class functionInterface {
    public static void main(String[] args) {
        // 函数型接口，操作T，返回R，方法apply
        Function<String,Integer> function2 = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        Function<String,Integer> function = s -> {return s.length();};
        System.out.println(function.apply("hello"));

        // 断定型接口，操作T，返回boolean，方法test
        Predicate<String> predicate2 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };
        Predicate<String> predicate = s -> { return s.isEmpty(); };
        System.out.println(predicate.test("hello"));

        // 消费型接口，操作S，无返回，方法accept
        /*Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };*/
        Consumer<String> consumer = s -> { System.out.println(s); };
        consumer.accept("hello");

        // 供给型接口，无输入，返回T，方法get
        /*Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "hello";
            }
        };*/
        Supplier<String> supplier = () -> {return "hello";};
        System.out.println(supplier.get());
    }
}
