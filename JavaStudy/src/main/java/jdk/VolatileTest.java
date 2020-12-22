package jdk;

/**
 * @author: Luo
 * @description:
 * @time: 2020/10/21 14:31
 * Modified By:
 */
public class VolatileTest {
    public static int count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 100; i++) {
                        count++;
                    }
                }
            });
            thread.start();
        }
        System.out.println(count);
    }
}
