package nioPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/8/13.
 */
public class BIOServer {
    public void server1(){
        try {
            ServerSocket serverSocket = new ServerSocket( 20006);
            System.out.println("服务器启动成功，监听端口等待客户端链接。。。");
            Socket socket = serverSocket.accept();

            OutputStream out = socket.getOutputStream();
            out.write("hello client, i am server".getBytes());
            out.flush();

            InputStream in = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            if( (len = in.read(buffer)) > 0){
                System.out.println(new String(buffer, 0 ,len));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void server2(){
        ServerSocket server = null;

        try {
            server = new ServerSocket(20006);

            System.out.println("服务器启动成功，监听端口等待客户端链接。。。");

            while (true){
                Socket socket = server.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OutputStream out = socket.getOutputStream();
                            out.write("接收到服务端信息".getBytes());
                            out.flush();

                            InputStream in = socket.getInputStream();
                            byte[] buffer = new byte[1024];
                            int len = 0;
                            if( (len = in.read(buffer)) > 0 ){
                                System.out.println(new String(buffer, 0 ,len));
                            }
                            System.out.println();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void server3(){
        ServerSocket server = null;
        ExecutorService executorService = Executors.newFixedThreadPool(180);

        try {
            server = new ServerSocket(20006);
            System.out.println("服务器启动成功，监听端口等待客户端链接。。。");

            while (true){
                Socket socket = server.accept();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("创建新线程");
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BIOServer server = new BIOServer();
        server.server2();
    }
}
