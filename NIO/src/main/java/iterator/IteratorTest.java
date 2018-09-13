package iterator;

/**
 * Created by Administrator on 2018/8/31.
 */
public class IteratorTest {
    String resource1 = "资源1";
    String resource2 = "资源2";
    Thread t1 = new Thread("线程1") {
        @Override
        public void run() {
            while (true) {
                synchronized (resource1) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (resource2) {
                        System.out.printf("线程1拥有[%s], 需要[%s]\n", resource1, resource2);
                    }
                }
            }
        }
    };
    Thread t2 = new Thread("线程2") {
        @Override
        public void run() {
            while (true) {
                synchronized (resource2) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (resource1) {
                        System.out.printf("线程2拥有[%s], 需要[%s]\n", resource2, resource1);
                    }
                }
            }
        }
    };
    public static void main(String a[]) {
        IteratorTest test = new IteratorTest();
        test.t1.start();
        test.t2.start();
    }
}





