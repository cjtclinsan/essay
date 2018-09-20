package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/9/15.
 * 一个线程或者多个线程，等待另外N个线程完成某件事情后才能执行
 * CountDownLatch是计数器，线程完成一个就递减一个，
 */
public class TestCountDownLatch {
    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Worker worker1 = new Worker("xiao li",5000, countDownLatch);
        Worker worker2 = new Worker("xiao wu", 8000, countDownLatch);
        Worker worker3 = new Worker("xiao si", 3000, countDownLatch);

        worker1.start();
        worker2.start();
        worker3.start();

        countDownLatch.await();

        System.out.println("all work done at "+sdf.format(new Date()));
    }

    static class Worker extends  Thread{
        String workerName;
        int workTime;
        CountDownLatch countDownLatch;

        public Worker(String workerName, int workTime, CountDownLatch countDownLatch) {
            this.workerName = workerName;
            this.workTime = workTime;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run(){
            System.out.println("Worker "+workerName+" do work begin at "+sdf.format(new Date()));
            doWork();
            System.out.println("Worker "+workerName+" do work complete at "+sdf.format(new Date()));
            countDownLatch.countDown();
            System.out.println(countDownLatch.getCount());
        }

        private void doWork(){
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
