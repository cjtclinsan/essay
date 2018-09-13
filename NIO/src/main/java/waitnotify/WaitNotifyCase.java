package waitnotify;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/8/24.
 */
public class WaitNotifyCase {
    public static void main(String[] args) {
        final Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread A is waiting to get lock");
                synchronized (lock){
                    System.out.println("thread A get lock");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println("thread A do wait method");
                        lock.wait();
                        System.out.println("wait A end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("thread C is waiting to get lock");
//                synchronized (lock){
//                    System.out.println("thread C get lock");
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                        System.out.println("thread C do wait method");
//                        lock.wait();
//                        System.out.println("wait C end");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread B is waiting to get lock");
                synchronized (lock){
                    System.out.println("thread B get lock");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        lock.notify();
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread B do notify method");
                }
            }
        }).start();

    }
}
