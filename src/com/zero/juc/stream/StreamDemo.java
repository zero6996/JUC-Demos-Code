package com.zero.juc.stream;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    private Integer id;
    private String Name;
    private  int age;
}

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/15 10:33
 * @Description: 练习题
 *  请按照给出数据，找出同时满足以下全部条件的用户
 *      偶数ID且年龄大于24
 *      且用户名转为大写
 *      且用户名字母倒序
 *      只输出一个用户名
 */

public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11,"a",23);
        User u2 = new User(12,"b",24);
        User u3 = new User(13,"c",22);
        User u4 = new User(14,"d",28);
        User u5 = new User(16,"e",26);
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        Predicate<User> judgment = user -> { return ((user.getId())%2==0 && user.getAge() > 24); };

        list.stream()
                .filter(user -> { return (user.getId() % 2 == 0 && user.getAge() > 24);})
                .map(user -> {return user.getName().toUpperCase();})
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .forEach(System.out::println);
    }
}