package jdk;

import java.util.HashMap;

/**
 * @author: Luo
 * @description:
 * @time: 2020/9/24 22:52
 * Modified By:
 */
public class Test {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = a;
        System.out.println(a==b);
        b++;    //进行拆箱，使用int进行运算
        System.out.println(a+","+b);
    }
}
