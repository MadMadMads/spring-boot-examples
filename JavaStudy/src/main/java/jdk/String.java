package jdk;

import java.util.StringTokenizer;

/**
 * @author: Luo
 * @description:String 类测试
 * @time: 2020/12/22 22:51
 * Modified By:
 */
class StringTest {
    String A = "abc";
    public void test1() {
        String B = "abc";
        System.out.println(A == B);
    }
    class test2 {
        String C = "abc";
        public void fun1() {
            String D = "abc";
            System.out.println(A == C);
            System.out.println(A == D);
        }
    }

    public static void main(String[] args) {
        StringTest stringTest = new StringTest();
        stringTest.test1();
        test2 test2 = stringTest.new test2();
        test2.fun1();
    }
}
