package jdk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Luo
 * @description:生产者消费者模型
 * @time: 2020/12/22 22:21
 * Modified By:
 */
public class Consumer {
    private final static int MAX_NUM = 100;
    private List<Object> list = new ArrayList<>();
    public void produce(int num) throws InterruptedException {
        synchronized (list) {
            while (list.size() + num > 100) {
                list.wait();
                System.out.println("条件不满足生产");
            }
            for (int i = 0; i < num;i++) {
                list.add(new Object());
            }
            System.out.println("生产" + num + "容量" + list.size());
            list.notifyAll();
        }
    }
    public void consume(int num) throws InterruptedException {
        synchronized (list) {
            while (list.size()<num) {
                list.wait();
                System.out.println("消费不满足");
            }
            for (int i = 0; i < num; i++) {
                list.remove(0);
            }
            System.out.println("已消费" + num);
            list.notifyAll();
        }
    }
}
