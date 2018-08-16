package nioPackageTwo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by Administrator on 2018/8/14.
 */
public class NIONetClient {
    private static class MyRunnable implements Runnable{

        private int idx = 0;

        public MyRunnable(int idx) {
            this.idx = idx;
        }

        @Override
        public void run() {
            SocketChannel sc = null;
            try {
                sc = SocketChannel.open();

                SocketAddress address = new InetSocketAddress("localhost", 20006);
                sc.connect(address);

                sendData(sc, "客户端线程"+idx+"连接");

                String str = receiveData(sc);

                if( null != str ){
                    System.out.println("The server reply:"+str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    sc.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static  void sendData(SocketChannel socketChannel, String msg) {
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String receiveData(SocketChannel channel){
        ByteBuffer bb = ByteBuffer.allocate(1024);
        StringBuilder msg = new StringBuilder();
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();

        try {
            if( channel.read(bb) > 0 ){
                bb.flip();
                msg.append(decoder.decode(bb));
                bb.clear();
            }
            return msg.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        for(int i = 1; i < 10; i++){
            new Thread(new MyRunnable(i)).start();
        }

    }
}
