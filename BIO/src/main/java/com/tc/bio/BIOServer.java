package com.tc.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author taosh
 * @create 2019-12-16 14:50
 */
public class BIOServer {
    //服务端网络IO模型的封装对象
    ServerSocket server;

    //服务器
    public BIOServer(int port){
        try {
            server = new ServerSocket(port);
            System.out.println("BIO服务器已经启动，监听端口为:"+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //开始监听
    public void listen(){
        while (true) {
            try {
                //等待客户端连接，阻塞方法
                //Tomcat 默认端口8080
                //Redis  6379
                //Zookeeper  2181

                //socket 是数据发送者在服务端的引用
                Socket client = server.accept();
                System.out.println("port:"+client.getPort());

                //读 Input
                InputStream is = client.getInputStream();

                //JVM内存，网络客户端把数据发送到网卡，将数据读到JVM中
                byte[] buff = new byte[1024];
                int len = is.read(buff);
                if( len > 0 ){
                    String msg = new String(buff, 0, len);
                    System.out.println("收到msg:"+msg);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BIOServer(8080).listen();
    }
}
