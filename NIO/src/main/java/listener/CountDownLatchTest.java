package listener;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/8/10.
 */
public class CountDownLatchTest {
    final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Worker worker1 = new Worker("zhao", 5000, latch);
        Worker worker2 = new Worker("qian", 4000, latch);
        Worker worker3 = new Worker("sun", 3000, latch);
        Worker worker4 = new Worker("li", 2000, latch);

        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();
    }

    static class Worker extends Thread{
        String workerName;
        int workTime;
        CountDownLatch latch;

        public Worker(String workerName, int workTime, CountDownLatch latch) {
            this.workerName = workerName;
            this.workTime = workTime;
            this.latch = latch;
        }

        @Override
        public void run(){
            doWork();//工作了
        }

        private void doWork(){
            System.out.println(workerName+"线程工作了");
            if( "zhao".equals(workerName) ){
                try {
                    latch.await();
                    System.out.println(workerName+"收尾工作结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            latch.countDown();
        }
    }
}
