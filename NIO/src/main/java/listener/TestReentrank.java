package listener;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/8/11.
 */
public class TestReentrank {
    public static void main(String[] args) throws InterruptedException {

        MyThread thread = new MyThread();
        thread.test();
    }


    static class MyThread extends Thread{
        public void test() throws InterruptedException {
            final Lock lock = new ReentrantLock();
            lock.lock();
            final Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    //lock.lock();
                    try {
                        Thread.sleep(2000);
                        lock.lockInterruptibly();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+" interropted");
                }
            },"child thread -1");
            t1.start();
            t1.interrupt();
            //lock.lockInterruptibly();
            Thread.sleep(1000000);
        }
    }
}
