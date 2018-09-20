package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2018/9/15.
 */
public class cyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("全部执行完毕");
            }
        });

        for( int i = 0; i < 5; i++ ){
            new Thread(new readNum(i,cyclicBarrier)).start();
        }
    }

    static class readNum implements Runnable{

        private int id;
        private CyclicBarrier cyclicBarrier;

        public readNum(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            synchronized (this){
                try {
                    System.out.println("线程组任务" + id + "结束，其他任务继续");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
