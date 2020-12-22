package jdk;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: Luo
 * @description:读写锁demo，读读不阻塞，读写阻塞，写写阻塞
 * @time: 2020/6/8 10:23
 * Modified By:
 */
public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int val = 0;

    /**
     * 处理读操作
     *
     * @throws InterruptedException
     */
    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            // 模拟读操作
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            System.out.println("读开始");
            // 读操作的耗时越多，读写锁的性能优势就越明显
            Thread.sleep(1000);
            return val;
        } finally {
            System.out.println("读结束");
            lock.unlock();
        }
    }

    /**
     * 处理写操作
     *
     * @throws InterruptedException
     */
    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            // 模拟写操作
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            System.out.println("begin write");
            Thread.sleep(1000);
            val = index;
        } finally {
            System.out.println("over write");
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnale = new Runnable() {
            @Override
            public void run() {
                try {
                    //demo.handleRead(readLock);

                    demo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //demo.handleWrite(writeLock, new Random().nextInt());
                    demo.handleWrite(lock, new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i=0; i<18; i++){
            new Thread(readRunnale).start();
        }

        for (int i=0; i<2; i++){
            new Thread(writeRunnable).start();
        }
    }
}
