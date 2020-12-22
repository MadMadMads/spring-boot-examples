package jdk;

import java.sql.Connection;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Luo
 * @description:交替打印A、B、C
 * @time: 2020/12/22 20:46
 * Modified By:
 */
public class ABC_Synch {
    private static Lock lock = new ReentrantLock();
    private static Condition A = lock.newCondition();
    private static Condition B = lock.newCondition();
    private static Condition C = lock.newCondition();
    private static int count = 0;
    static class ThreadA implements Runnable{
        @Override
        public void run() {
            lock.lock();
            try {
            for (int i = 0; i < 10; i++) {
                if (count % 3 != 0) A.await();
                System.out.print("A");
                count++;
                B.signal();
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            } }
    }
    static class ThreadB implements Runnable{
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    if (count % 3 != 1) B.await();
                    System.out.print("B");
                    count++;
                    C.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            } }
    }
    static class ThreadC implements Runnable{
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    if (count % 3 != 2) C.await();
                    System.out.print("C");
                    count++;
                    A.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            } }
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(new ThreadA());
        Thread threadB = new Thread(new ThreadB());
        Thread threadC = new Thread(new ThreadC());
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
