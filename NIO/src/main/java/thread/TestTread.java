package thread;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/9/15.
 */
public class TestTread {
    public static void main(String[] args) {
       ThreadCount tc = null;
       ExecutorService es = Executors.newCachedThreadPool();

       CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(es);

       for( int i = 0; i < 4; i++ ){
           tc = new ThreadCount(i+1);
           cs.submit(tc);         //返回值为future类型
       }

       es.shutdown();
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

    //有返回值的多线程
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
                Thread.sleep(1000);
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
