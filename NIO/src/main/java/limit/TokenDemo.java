package limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/9/6.
 */
public class TokenDemo {
    private int qps;          //qps
    private int countOfRes;   //总请求数量

    private RateLimiter rateLimiter;

    public TokenDemo(int qps, int countOfRes) {
        this.qps = qps;
        this.countOfRes = countOfRes;
    }

    //构建一个令牌桶
    public TokenDemo processWithTokenBucket(){
        rateLimiter = RateLimiter.create(qps);
        return this;
    }

    //构建一个漏桶
    public TokenDemo processWithLeakyBucket(){
        rateLimiter = RateLimiter.create(qps, 1000, TimeUnit.MILLISECONDS);
        return this;
    }

    private void processRequest(){
        System.out.println("Ratelimiter:"+rateLimiter.getClass());

        long start = System.currentTimeMillis();
        for( int i = 0; i < countOfRes; i++ ){
            rateLimiter.acquire();        //获取令牌，阻塞
        }
        long end = System.currentTimeMillis()-start;

        System.out.println(end);
        System.out.println("请求处理总数："+countOfRes+"," +
                "耗时："+end+"毫秒，qps："+qps+"，" +
                "实际qps："+Math.ceil(countOfRes/(end/1000.0)));
    }

    public void doProcess() throws InterruptedException {
        for( int i = 0; i < 5; i ++ ){
            processRequest();
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //以漏桶方式处理请求
        new TokenDemo(50,250).processWithLeakyBucket().doProcess();

        //以令牌桶方式处理请求
        //new TokenDemo(50,250).processWithTokenBucket().doProcess();
    }
}
