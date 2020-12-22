package lambda;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * @author: Luo
 * @description:
 * @time: 2020/7/1 22:42
 * Modified By:
 */
public class CompletableFutureTest {

    public static void main(String[] args) {

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "f1";
        });

        f1.whenCompleteAsync(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println(System.currentTimeMillis() + ":" + s);
            }
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "f2";
        });

        f2.whenCompleteAsync(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println(System.currentTimeMillis() + ":" + s);
            }
        });

        CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2);

        //阻塞，直到所有任务结束。
        System.out.println(System.currentTimeMillis() + ":阻塞");
        all.join();
        System.out.println(System.currentTimeMillis() + ":阻塞结束");

        //一个需要耗时2秒，一个需要耗时3秒，只有当最长的耗时3秒的完成后，才会结束。
        System.out.println("任务均已完成。");
    }
}
