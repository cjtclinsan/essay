package limit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by Administrator on 2018/9/6.
 */
public class RateLimiterDemo {
    public static void testNoRateLimiter(){
        long start = System.currentTimeMillis();
        for( int i = 0; i < 50; i++ ){
            System.out.println("call execute.."+i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start+"--毫秒");
    }

    public static void testWithRateLimiter(){
        long start = System.currentTimeMillis();
        RateLimiter rateLimiter = RateLimiter.create(10);
        for( int i = 0; i < 50; i++ ){
            rateLimiter.acquire();
            System.out.println("rateLimiter execute.."+i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start+"~~毫秒");
    }

    public static void main(String[] args) {
        testNoRateLimiter();

        testWithRateLimiter();
    }
}
