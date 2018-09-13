package listener;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/8/10.
 * N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new MainTask()); //传入参数含义：线程同步个数，汇总线程
        new SubTask("A",cyclicBarrier).start();
        new SubTask("B",cyclicBarrier).start();
        new SubTask("C",cyclicBarrier).start();
        new SubTask("D",cyclicBarrier).start();
        new SubTask("E",cyclicBarrier).start();
    }
}

class MainTask implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("........执行最后任务........");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SubTask extends Thread{
    private String name;
    private CyclicBarrier cb;

    public SubTask(String name, CyclicBarrier cb) {
        this.name = name;
        this.cb = cb;
    }

    @Override
    public void run(){
        System.out.println("[并发任务" + name + "]  开始执行");
        for( int i = 0; i < 100; i++ ){
            System.out.println("[并发任务" + name + "]  开始执行完毕，通知障碍器");
        }

        try {
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}