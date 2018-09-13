package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2018/9/3.
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        //创建线程池
        int count = 5;
        ExecutorService threadPool = Executors.newFixedThreadPool(count);

        //控制信号量，即线程并发数2
        Semaphore bankers = new Semaphore(3);

        //当前客户编号
        int customer = 0;
        System.out.println("开始·：空闲业务员有： "+bankers.availablePermits()+" 位");


        for( int i = 1; i <= count; i++ ){
            customer = i;
            Bank bank = new Bank(bankers,customer);
            threadPool.execute(bank);
        }
        Thread.sleep(3000);
        threadPool.shutdown();
        System.out.println("结束：空闲业务员有： "+bankers.availablePermits()+" 位");
    }
}

class Bank implements Runnable{

    private Semaphore bankers;
    private int consumer;

    public Bank(Semaphore bankers, int consumer) {
        this.bankers = bankers;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        System.out.println("======客户进入银行先拿号，号码："+consumer+"，请等待叫号=========");
        try {
            //客户办理业务阶段，限制最多只能有2位客户同时办理业务

            //业务开始办理，业务员被占用；
            bankers.acquire();
            System.out.println("客户："+consumer+" 开始办理业务，start");
            System.out.println("还剩："+bankers.availablePermits()+"位业务员空闲");

            Thread.sleep(200);

            System.out.println("客户："+consumer+" 办理业务完毕，end");

            //业务办理完成后，release业务员
            bankers.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}