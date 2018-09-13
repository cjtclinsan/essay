package listener;

/**
 * Created by Administrator on 2018/8/10.
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadJoinTest t1 = new ThreadJoinTest("xiaoming");
        ThreadJoinTest t2 = new ThreadJoinTest("xiaodong");

        t1.start();
        t1.join(10);

        t2.start();

    }


}

class ThreadJoinTest extends Thread{
    public ThreadJoinTest(String name) {
        super(name);
    }
    @Override
    public void run(){
        for(int i=0;i<1000;i++){
            System.out.println(this.getName() + ":" + i);
        }
    }
}
