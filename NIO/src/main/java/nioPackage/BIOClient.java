package nioPackage;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2018/8/13.
 */
public class BIOClient {
    public static void main(String[] args) throws InterruptedException {
        try {
            System.out.println("客户端启动...");
            System.out.println("当接收到服务器端字符为 \"bye\" 的时候, 客户端将终止\n");

            Socket socket = new Socket("localhost", 20006);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
            //StringBuffer str = new StringBuffer("hello server, i am client"+i);
            out.writeUTF(str);
            out.flush();

            DataInputStream in = new DataInputStream(socket.getInputStream());
            //byte[] buffer = new byte[1024];
            //int len = 0;
            String strIn = in.readUTF();
            System.out.println("客户端接收: " + strIn);

            if("bye".equals(strIn)){
                out.close();
                in.close();
                System.out.println("客户端将关闭连接");
                Thread.sleep(500);
            }
            if( socket != null ){
                socket.close();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
