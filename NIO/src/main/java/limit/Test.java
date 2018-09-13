package limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/9/6.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(0.5);
        List<Runnable> task = new ArrayList<>();
        for( int i = 0; i < 10; i++ ){
            task.add(new UserRequest(i));
        }

        ExecutorService service = Executors.newCachedThreadPool();
        for (Runnable runnable : task) {
            System.out.println("等待时间:"+limiter.acquire());
            service.execute(runnable);
        }
    }

    private static class UserRequest implements Runnable{

        private int id;

        public UserRequest(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(id);
        }
    }
}
