package listener;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/8/10.
 */
public class TestThread {
    public static void main(String[] args) {
        ThreadCount count = null;
        ExecutorService executorService = new ThreadPoolExecutor(0,Integer.MAX_VALUE,60, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(executorService);

        for( int i = 0; i< 4; i++ ){
            count = new ThreadCount(i+1);
            cs.submit(count);
        }

        executorService.shutdown();

        int total = 0;
        for( int i = 0; i < 4; i++ ){
            try {
                total += cs.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(total);
    }

    static class ThreadCount implements Callable<Integer>{
        private int type;

        public ThreadCount(int type) {
            this.type = type;
        }

        @Override
        public Integer call() throws Exception {
            if( type == 1 ){
                System.out.println("c盘统计数据");
                return 1;
            }else if( type == 2 ){
                Thread.sleep(10000);
                System.out.println("D盘统计数据");
                return 2;
            }else if( type == 3 ){
                System.out.println("E盘统计数据");
                return 3;
            }else if( type == 4 ){
                System.out.println("F盘统计数据");
                return 4;
            }
            return null;
        }
    }
}
