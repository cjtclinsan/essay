package limit;

import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/9/6.
 */
public class ListenableFutureDemo {
    /**
     * RateLimiter类似于JDK的信号量Semaphore，他用来限制对资源并发访问的线程数
     */
    public static void testRateLimiter(){
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        RateLimiter limiter = RateLimiter.create(5);

        for ( int i = 0; i < 20; i++ ){
            limiter.acquire();   // 请求RateLimiter, 超过permits会被阻塞

            final ListenableFuture<Integer> listenableFuture =
                    executorService.submit(new Task("is "+i));
        }
    }


    public static void testListenableFuture(){
        ListeningExecutorService executorService = MoreExecutors
                .listeningDecorator(Executors.newCachedThreadPool());

        final ListenableFuture<Integer> listenableFuture = executorService
                .submit(new Task("testListenableFuture"));

        //同步获取调用结果
        try {
            System.out.println(listenableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //一
        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("get listenable future's result "
                            + listenableFuture.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        },executorService);

        //二
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer integer) {
                System.out
                        .println("get listenable future's result with callback "
                                + integer);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        testRateLimiter();

        testListenableFuture();
    }
}

class Task implements Callable<Integer>{
    String str;

    public Task(String str) {
        this.str = str;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("call execute :"+str);
        TimeUnit.SECONDS.sleep(1);
        return 7;
    }
}
