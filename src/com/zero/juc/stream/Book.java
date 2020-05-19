package com.zero.juc.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: zero <[email] 1490829140@qq.com>
 * @Date: Create in 2020/5/15 9:37
 * @Description: 链式编程+流式计算
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true) // 开启链式编程
public class Book {
    private int id;
    private String bookName;
    private double price;

}
