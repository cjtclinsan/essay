package socketclient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by Administrator on 2018/7/17.
 */
public class ChatClient {
    public static void main(String[] args) {

        for(int i = 1; i < 100; i++){
            new Thread(new MyRunnable(i)).start();
        }

    }

    private static class MyRunnable implements Runnable{
        private final int idx;

        private MyRunnable(int idx){
            this.idx = idx;
        }

        @Override
        public void run() {
            SocketChannel ssc = null;
            try {
                ssc = SocketChannel.open();

                SocketAddress socketAddress = new InetSocketAddress("localhost",8080);

                ssc.connect(socketAddress);

                SendAndReceiveUtil.sendData(ssc,"My id is " + idx);

                String msg = SendAndReceiveUtil.receiveData(ssc);
                if( null != msg ){
                    System.out.println("The server reply:"+msg);
                    SendAndReceiveUtil.sendData(ssc,"bye");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    ssc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
