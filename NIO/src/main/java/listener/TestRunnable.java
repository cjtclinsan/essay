package listener;

/**
 * Created by Administrator on 2018/8/11.
 */
public class TestRunnable implements Runnable{
    public static void main(String[] args) {
//        TestRunnable testRunnable = new TestRunnable();
//        new Thread(testRunnable).start();
//        new Thread(testRunnable).start();
//        new Thread(testRunnable).start();
        String str = "abc";
        int hash = 0;
        char[] var = str.toCharArray();
        for( int i = 0; i < str.length(); i++ ){
            System.out.println(var[i]);
            hash = 31*hash+var[i];
            System.out.println(hash);
        }
    }


    private int ticketNum = 10;
    @Override
    public void run() {
        for( int i = 0; i < 20; i++ ){
            if (this.ticketNum > 0) {
                ticketNum--;
                System.out.println("总共10张,卖掉1张，剩余" + ticketNum);
            }
        }
    }
}
