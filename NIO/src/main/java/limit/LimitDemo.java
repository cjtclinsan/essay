package limit;

import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/9/6.
 * 限流
 */
public class LimitDemo {
    RateLimiter rateLimiter = RateLimiter.create(2);  //busty  warmup

    //busty token ducket   基于令牌桶
    //warmup leaky bucket  基于漏桶

    public void doRequest(){
        if( rateLimiter.tryAcquire() ){   //获取令牌桶的过程
            System.out.println(Thread.currentThread().getName()+":成功获取令牌..");
        }else{   //令牌获取失败，选择处理方式，拒绝/排队
            System.out.println(Thread.currentThread().getName()+":当前访问量过大，稍后再试..");
        }
    }

    public static void main(String[] args) throws IOException {
        LimitDemo limitDemo = new LimitDemo();
        CountDownLatch latch = new CountDownLatch(1);
        Random random = new Random();
        for ( int i = 0; i <= 20; i++ ) {
            new Thread(()->{
                try {
                    latch.await();
                    Thread.sleep(random.nextInt(2000));
                    limitDemo.doRequest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"t-"+i).start();
        }
        latch.countDown();
        System.in.read();
    }
}
