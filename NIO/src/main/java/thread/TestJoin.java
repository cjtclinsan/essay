package thread;

/**
 * Created by Administrator on 2018/9/15.
 * join会等待调用它的线程完成后，才能继续往下运行，
 * 使得线程之间的并行执行变成串行
 */
public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new ThreadName("thread-1");
        Thread t2 = new ThreadName("thread-2");
        Thread t3 = new ThreadName("thread-3");
        Thread t4 = new ThreadName("thread-4");

        Thread t5 = new ThreadName("thread-5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        t5.start();
        t5.join();
    }

    static class ThreadName extends Thread{
        private String name;

        public ThreadName(String name) {
            this.name = name;
        }

        @Override
        public void run(){
            System.out.println(name);
        }
    }
}
