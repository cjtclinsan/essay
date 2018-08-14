package sample.servlet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Administrator on 2018/7/22.
 */
public class TestServer {
    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动，等待客户端发送链接……");
        byte[] buf = new byte[1024];
        DatagramSocket dSocket = new DatagramSocket(3000);
        DatagramPacket dp_receive = new DatagramPacket(buf, 1024);
        boolean f = true;
        while ( f ){
            System.out.println("dp_receive:"+dp_receive);
            dSocket.setSoTimeout(5000);
            dSocket.receive(dp_receive);
            System.out.println("服务器从客户端获取：");
            String str_reveive = new String(dp_receive.getData(), 0, dp_receive.getLength())+" from  "+
                    dp_receive.getAddress().getHostAddress()+":"+dp_receive.getPort();
            System.out.println(str_reveive);
            DatagramPacket dp_send = new DatagramPacket(str_reveive.getBytes(),str_reveive.length(),dp_receive.getAddress(), dp_receive.getPort());
            dSocket.send(dp_send);
            dp_receive.setLength(1024);
        }
        dSocket.close();
    }
}
