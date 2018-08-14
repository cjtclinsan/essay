package sample.servlet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Administrator on 2018/7/22.
 */
public class TestSocket {
    private static final int BUFSIZE = 32;

    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动");
        TestSocket socket = new TestSocket();
        socket.init();
    }

    public void init() throws IOException {
        ServerSocket serverSocket = new ServerSocket(20006);
        Socket socket = new Socket();

        while ( true ){
            socket = serverSocket.accept();
            SocketAddress clientAddress = socket.getRemoteSocketAddress();
            System.out.println("Handling client at "+clientAddress+" 与客户端连接成功！");
            new HandlerThread(socket);
        }
    }

    private class HandlerThread implements Runnable{
        public  Socket socket;
        public HandlerThread(Socket client){
            socket = client;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                String clientInputStr = inputStream.readUTF();
                System.out.println("服务端收到:"+clientInputStr);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                System.out.println("服务端输入:");
                String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
                System.out.println(socket+"   "+out);
                out.writeUTF(s);

                boolean flag = true;
                while(flag){
                    if( clientInputStr == null || "".equals(clientInputStr) ){
                        flag = false;
                    }else{
                        if( "bye".equals(clientInputStr) ){
                            flag = false;
                            System.out.println("客户端说byebye！");
                            socket.close();
                        }
                    }
                }
                out.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if( socket != null ){
                    try {
                        socket.shutdownOutput();
                    } catch (IOException e) {
                        socket = null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }
}
