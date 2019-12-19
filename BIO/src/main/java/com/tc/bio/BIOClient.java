package com.tc.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * @author taosh
 * @create 2019-12-16 14:49
 */
public class BIOClient {
    public static void main(String[] args) throws IOException {
        //要和谁进行通信  服务器的IP,端口
        Socket server = new Socket("localhost", 8080);

        //输出 o
        OutputStream os = server.getOutputStream();

        String name = UUID.randomUUID().toString();
        System.out.println("客户端发送数据:"+name);

        os.write(name.getBytes());

        os.close();
        server.close();
    }
}
