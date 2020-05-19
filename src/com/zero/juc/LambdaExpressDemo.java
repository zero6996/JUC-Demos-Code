package com.zero.juc;

@FunctionalInterface
interface Foo{
//    public void sayHello();
    public int add(int x,int y);
    public default int mul(int x,int y){
        return x * y;
    }
    public static int diy(int x,int y){
        return x/y;
    }
}


/**
 * @Author: zero
 * @Description: lambda表达式练习
 *  函数式接口
 * 1. 小口诀：拷贝中括号，写死右箭头，落地大括号
 * 2. @FunctionalInterface
 * 3. default
 * 4. static
 * Date: Create in 2020/5/4 15:12
 * Modified By:
 */

public class LambdaExpressDemo {
    public static void main(String[] args) {
//        Foo foo = new Foo(){
//            @Override
//            public void sayHello(){
//                System.out.println("hello world");
//            }
//
//            @Override
//            public int add(int x, int y) {
//                return 0;
//            }
//        };
//        foo.sayHello();
        Foo foo = (int x,int y)-> {
            System.out.println("come in add method");
            return x + y;
        };
        int add = foo.add(3, 5);
        System.out.println(add);
        System.out.println(foo.mul(3,5));
        System.out.println(Foo.diy(6,3));
//        int i = 1;
//        i = i++;
//        System.out.println(i++);
    }
}
