package jdk;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * @author: Luo
 * @description:
 * @time: 2020/10/13 11:19
 * Modified By:
 */
public class CallableTest {
    public static class Run implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("开始");
            Thread.sleep(3000);
            return "test";
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("1");
        Future future = executorService.submit(new Run());
        System.out.println("2");
        Runnable target;
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
