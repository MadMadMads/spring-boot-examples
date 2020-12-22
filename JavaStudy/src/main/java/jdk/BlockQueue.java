package jdk;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Luo
 * @description:阻塞队列实现
 * @time: 2020/12/22 20:22
 * Modified By:
 */
public class BlockQueue<E> {
    private Object[] items;
    private int headIndex;
    private int tailIndex;
    private int count;
    private ReentrantLock lock = new ReentrantLock();
    private final Condition Empty = lock.newCondition();
    private final Condition Full = lock.newCondition();
    public void put (E e) {
        lock.lock();
        try {
            if (count == items.length) {
                Full.await();
            }
            items[tailIndex] = e;
            if (++tailIndex == items.length) {
                tailIndex = 0;
            }
            count++;
            Empty.signal();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public Object get() throws InterruptedException {
        lock.lock();
        try {
            if (count == 0) {
                Empty.await();
            }
            Object element = items[headIndex];
            // 防止内存泄漏
            items[headIndex] = null;
            if (++headIndex == items.length) {
                headIndex = 0;
            }
            count--;
            Full.signal();
            return element;
        }  finally {
            lock.unlock();
        }
    }
}
