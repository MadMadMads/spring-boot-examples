package jdk;

/**
 * @author: Luo
 * @description:死锁的实现
 * @time: 2020/10/13 13:58
 * Modified By:
 */
public class DeadLock {
    static class Dead implements Runnable{
        //标志位
        private boolean flag;

        Dead(boolean flag){
            this.flag = flag;
        }

        //复写run方法
        public void run(){
            while(true){
                if(flag){
                    //拥有locka，想获取lockb
                    synchronized(Lock.locka){
                        System.out.println("if locka");

                        synchronized(Lock.lockb){
                            System.out.println("if lockb");
                        }
                    }
                }
                else{
                    //拥有lockb，想获取locka
                    synchronized(Lock.lockb){
                        System.out.println("else lockb");

                        synchronized(Lock.locka){
                            System.out.println("else locka");
                        }
                    }
                }
            }
        }
    }

    //定义锁
    static class Lock {
        static Object locka = new Object();
        static Object lockb = new Object();
    }

    public static void main(String[] args){
        //创建线程
        Thread t1 = new Thread(new Dead(true));
        Thread t2 = new Thread(new Dead(false));

        //开启线程
        t1.start();
        t2.start();
    }

}